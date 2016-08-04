package la.netco.generico.servicios.impl;

import javax.annotation.Resource;

import la.netco.generico.servicios.ServiceDao;
import la.netco.generico.servicios.dao.GenericCommonDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("serviceDao")
public class ServiceDaoImpl implements ServiceDao {

	private GenericCommonDao genericCommonDao;
		
	
	public GenericCommonDao getGenericCommonDao() {
		return genericCommonDao;
	}
	
	@Autowired
	@Resource(name="genericCommonDao")
	public void setGenericCommonDao(GenericCommonDao genericCommonDao) {
		this.genericCommonDao = genericCommonDao;
	}
	

}
