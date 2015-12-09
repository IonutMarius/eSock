package ro.esock.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class HelloService {

	private List<String> nameList;
	
	public HelloService(){
		nameList = new ArrayList<>();
		nameList.add("World");
		nameList.add("Johnny");
		nameList.add("Stef");
	}
	
	public String getName(Integer id){
		if (nameList.size() > id){
			return nameList.get(id);
		}
		else{
			return "Annonymous";
		}
	}
}
