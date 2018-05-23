package sebamed.clothesshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sebamed.clothesshop.domain.Services;
import sebamed.clothesshop.service.ServiceService;

@RestController
@RequestMapping("/api/services")
@CrossOrigin("http://localhost:4200")
public class ServiceAPI {

	@Autowired
	ServiceService serviceService;

	@GetMapping("/")
	public List<Services> getAllServices(){
		return this.serviceService.getAll();
	}
	
	@GetMapping("/generate")
	public void generateServices() {

		Services s = new Services();
		s.setIcon("cog");
		s.setText("Lorem ipsum");
		s.setTitle("Prvi");
		
		Services s2 = new Services();
		s2.setIcon("bars");
		s2.setText("Dolor sit");
		s2.setTitle("Amet");
		
		Services s3 = new Services();
		s3.setIcon("search");
		s3.setText("Dolor awdasa");
		s3.setTitle("Treci");
		
		this.serviceService.addService(s);
		this.serviceService.addService(s2);
		this.serviceService.addService(s3);
	}

	@PostMapping("/update")
	public void updateServices(@RequestBody List<Services> services) {
		for (int i = 0; i < services.size(); i++) {
			this.serviceService.updateService(services.get(i)).setTitle(services.get(i).getTitle());
			this.serviceService.updateService(services.get(i)).setText(services.get(i).getText());
			this.serviceService.updateService(services.get(i)).setIcon(services.get(i).getIcon());
			this.serviceService.save(services.get(i));
		}
	}

}
