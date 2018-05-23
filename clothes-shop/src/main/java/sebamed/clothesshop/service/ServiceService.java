package sebamed.clothesshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sebamed.clothesshop.domain.Services;
import sebamed.clothesshop.repository.ServiceRepository;

@Service
public class ServiceService {

	@Autowired
	ServiceRepository serviceRepository;
	
	public Services addService(Services service) {
		return this.serviceRepository.save(service);
	}
	
	public Services updateService(Services service) {
		return this.serviceRepository.findOneById(service.getId());
	}
	
	public Services save(Services service) {
		return this.serviceRepository.save(service);
	}
	
	public List<Services> getAll(){
		return this.serviceRepository.findAll();
	}

	
}
