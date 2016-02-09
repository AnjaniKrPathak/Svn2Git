package com.clinakos.common.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.definition.KnowledgePackage;
import org.drools.runtime.StatefulKnowledgeSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.clinakos.data.model.rules.Message;
import com.clinakos.common.rules.KnowledgePackageUtil;

public class RulesUtil {
	
	private static KnowledgeBase knowledgeBase;
	private static StatefulKnowledgeSession session ;
	static {
		List<KnowledgePackage> packages = new ArrayList<KnowledgePackage>();
		try {
			KnowledgePackageUtil.loadPackages("pkgs",packages);
		} catch (Exception e) {
			e.printStackTrace();
		}
		knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();
		knowledgeBase.addKnowledgePackages(packages);
		session=knowledgeBase.newStatefulKnowledgeSession();
	}
	
	public static void process(Message msg){
		
		session.setGlobal("mapper", new ObjectMapper());
		session.setGlobal("decimalFormat", new DecimalFormat());
		session.insert(msg);
		
		
	}
	public static void fireRuleToDroolEngine(){
		session.fireAllRules();
	}
	
	

}
