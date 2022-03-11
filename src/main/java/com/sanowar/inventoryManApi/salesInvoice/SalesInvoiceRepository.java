package com.sanowar.inventoryManApi.salesInvoice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sanowar.inventoryManApi.base.BaseRepository;
import com.sanowar.inventoryManApi.pagination.DataTableRequest;
import com.sanowar.inventoryManApi.pagination.DataTableResults;
import com.sanowar.inventoryManApi.pagination.PaginationCriteria;
import com.sanowar.inventoryManApi.util.Response;

/**
 * @author Md. Sanowar Ali
 *
 */

@Repository
@Transactional
public class SalesInvoiceRepository extends BaseRepository {

	// Grid List
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Response gridList(HttpServletRequest request) {
		Response response = new Response();
		DataTableResults<SalesInvoiceEntity> dataTableResults = null;

		SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();
		DataTableRequest dataTableInRQ = new DataTableRequest(request);
		List gridList = new ArrayList<>();

		// String id = request.getParameter("id");
		// if (id != null) {
		// infertilityLookupDtlEntity.setId(Long.parseLong(id));
		// }

		Long totalRowCount = totalCount(salesInvoiceEntity);
		response = baseList(typedQuery(salesInvoiceEntity, dataTableInRQ));
		if (response.isSuccess()) {
			if (response.getItems() != null) {
				gridList = response.getItems();
			}
			dataTableResults = dataTableResults(dataTableInRQ, countTypedQuery(salesInvoiceEntity, dataTableInRQ),
					gridList, totalRowCount);
		}
		response.setItems(null);
		response.setObj(dataTableResults);
		return response;
	}

	// Get All List
	public Response getAllList(String reqObj) {
		SalesInvoiceEntity salesInvoiceEntity = null;
		if (null != reqObj) {
			salesInvoiceEntity = objectMapperReadValue(reqObj, SalesInvoiceEntity.class);
		}
		return baseList(criteriaQuery(salesInvoiceEntity));
	}

	// findById or any other response type
	public Response findById(Long id) {
		Response response = new Response();
		SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();

		salesInvoiceEntity.setId(id);
		response = baseList(criteriaQuery(salesInvoiceEntity));
		if (response.isSuccess()) {
			return response;
		}
		return getErrorResponse("Record not Found !!");
	}

	// FindById Entity Type
	public SalesInvoiceEntity findId(Long id) {
		SalesInvoiceEntity salesInvoiceEntity = new SalesInvoiceEntity();

		salesInvoiceEntity.setId(id);
		Response response = baseFindById(criteriaQuery(salesInvoiceEntity));
		if (response.isSuccess()) {
			return getValueFromObject(response.getObj(), SalesInvoiceEntity.class);
		}
		return null;
	}

	// Save
	public Response save(String reqObj) {
		Response response = new Response();
		SalesInvoiceEntity salesInvoiceObj = objectMapperReadValue(reqObj, SalesInvoiceEntity.class);
		if (salesInvoiceObj == null) {
			return getErrorResponse("Data not found for Save!");
		}
		// Long companyNo = 1L;
		// salesInvoiceObj.setId(generatesalesInvoiceNo(companyNo));
		response = baseOnlySave(salesInvoiceObj);
		if (response.isSuccess()) {
			response.setMessage("Successsfully Data Saved!");
			return response;
		}
		return getErrorResponse("Data Not Saved!");
	}

	// Update
	public Response update(String reqObj) {
		Response response = new Response();
		SalesInvoiceEntity salesInvoiceObj = objectMapperReadValue(reqObj, SalesInvoiceEntity.class);
		SalesInvoiceEntity obj = findId(salesInvoiceObj.getId());
		if (obj != null) {
			response = baseUpdate(salesInvoiceObj);
			if (response.isSuccess()) {
				response.setMessage("Successsfully Data Updated!");
				return response;
			}
		}
		return getErrorResponse("Data not Found for Update!");
	}

	// Delete
	public Response delete(Long id) {
		SalesInvoiceEntity salesInvoiceObj = findId(id);
		if (salesInvoiceObj == null) {
			return getErrorResponse("Record not found!");
		}
		return baseDelete(salesInvoiceObj);
	}

	private Long totalCount(SalesInvoiceEntity filter) {
		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = longCriteriaQuery(builder);
		Root<SalesInvoiceEntity> root = from(SalesInvoiceEntity.class, criteriaQuery);
		return totalCount(builder, criteriaQuery, root, criteriaCondition(filter, builder, root));
	}

	@SuppressWarnings({ "unchecked" })
	private List<Predicate> criteriaCondition(SalesInvoiceEntity filter, CriteriaBuilder builder,
			Root<SalesInvoiceEntity> root) {
		if (builder == null) {
			builder = super.builder;
		}
		if (root == null) {
			root = super.root;
		}
		List<Predicate> p = new ArrayList<Predicate>();
		if (filter != null) {
			if (filter.getId() != null && filter.getId() > 0) {
				Predicate condition = builder.equal(root.get("id"), filter.getId());
				p.add(condition);
			}
		}
		return p;
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	private void init() {
		initEntityManagerBuilderCriteriaQueryRoot(SalesInvoiceEntity.class);
		CriteriaBuilder builder = super.builder;
		CriteriaQuery criteria = super.criteria;
		Root root = super.root;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private CriteriaQuery criteriaQuery(SalesInvoiceEntity filter) {
		init();
		List<Predicate> p = new ArrayList<Predicate>();
		p = criteriaCondition(filter, null, null);

		if (!CollectionUtils.isEmpty(p)) {
			Predicate[] pArray = p.toArray(new Predicate[] {});
			Predicate predicate = builder.and(pArray);
			criteria.where(predicate);
		}
		return criteria;
	}

	@SuppressWarnings({ "rawtypes" })
	private <T> TypedQuery typedQuery(SalesInvoiceEntity filter, DataTableRequest<T> dataTableInRQ) {
		init();
		List<Predicate> pArrayJoin = new ArrayList<Predicate>();
		List<Predicate> pConjunction = criteriaCondition(filter, null, null);
		List<Predicate> pDisJunction = dataTablefilter(dataTableInRQ);
		Predicate predicateAND = null;
		Predicate predicateOR = null;

		if (!CollectionUtils.isEmpty(pConjunction)) {
			Predicate[] pArray = pConjunction.toArray(new Predicate[] {});
			predicateAND = builder.and(pArray);
		}
		if (!CollectionUtils.isEmpty(pDisJunction)) {
			Predicate[] pArray = pDisJunction.toArray(new Predicate[] {});
			predicateOR = builder.or(pArray);
		}
		if (predicateAND != null) {
			pArrayJoin.add(predicateAND);
		}
		if (predicateOR != null) {
			pArrayJoin.add(predicateOR);
		}
		if (dataTableInRQ.getOrder().getName() != null && !dataTableInRQ.getOrder().getName().isEmpty()) {
			if (dataTableInRQ.getOrder().getSortDir().equals("ASC")) {
				criteria.orderBy(builder.asc(root.get(dataTableInRQ.getOrder().getName())));
			} else {
				criteria.orderBy(builder.desc(root.get(dataTableInRQ.getOrder().getName())));
			}

		}
		criteria.where(pArrayJoin.toArray(new Predicate[0]));
		return baseTypedQuery(criteria, dataTableInRQ);
	}

	private <T> Long countTypedQuery(SalesInvoiceEntity filter, DataTableRequest<T> dataTableInRQ) {

		if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
			return 0l;
		}

		CriteriaBuilder builder = criteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = longCriteriaQuery(builder);
		Root<SalesInvoiceEntity> root = from(SalesInvoiceEntity.class, criteriaQuery);
		return totalCount(builder, criteriaQuery, root, criteriaCondition(filter, builder, root),
				dataTablefilter(dataTableInRQ, builder, root));
	}

	@Override
	public <T> List<Predicate> dataTablefilter(DataTableRequest<T> dataTableInRQ, CriteriaBuilder builder, Root root) {
		PaginationCriteria paginationCriteria = dataTableInRQ.getPaginationRequest();
		paginationCriteria.getFilterBy().getMapOfFilters();
		List<Predicate> p = new ArrayList<Predicate>();

		if (!paginationCriteria.isFilterByEmpty()) {
			Iterator<Entry<String, String>> fbit = paginationCriteria.getFilterBy().getMapOfFilters().entrySet()
					.iterator();
			while (fbit.hasNext()) {
				Map.Entry<String, String> pair = fbit.next();
				if (!pair.getKey().equals("ssModifiedOn")) {

					// System.out.println("pair.getKey() " + pair.getKey());

					p.add(builder.like(builder.lower(root.get(pair.getKey())), pair.getValue().toLowerCase()));

				}
			}

		}
		return p;
	}

}
