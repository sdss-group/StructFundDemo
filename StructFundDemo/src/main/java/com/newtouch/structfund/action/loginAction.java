package com.newtouch.structfund.action;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
@SuppressWarnings("all")
public class loginAction {
	
	@RequestMapping("login")
	public String login(HttpServletRequest req) {
		Map map=req.getParameterMap();  
	    Set keSet=map.entrySet();  
	    System.out.println(keSet.size());
	    for(Iterator itr=keSet.iterator();itr.hasNext();){  
	        Map.Entry me=(Map.Entry)itr.next();  
	        Object ok=me.getKey();  
	        Object ov=me.getValue();  
	        String[] value=new String[1];  
	        if(ov instanceof String[]){  
	            value=(String[])ov;  
	        }else{  
	            value[0]=ov.toString();  
	        }  
	  
	        for(int k=0;k<value.length;k++){  
	            System.out.println(ok+"="+value[k]);  
	        }  
	      }  

		return "success";
		
	}

}
