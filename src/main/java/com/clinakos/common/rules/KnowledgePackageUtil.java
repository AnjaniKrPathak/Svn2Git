package com.clinakos.common.rules;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Collection;

import org.drools.core.definitions.impl.KnowledgePackageImp;
import org.drools.definition.KnowledgePackage;
import org.drools.impl.adapters.KnowledgePackageAdapter;


public class KnowledgePackageUtil {
	
	public static void loadPackages(String location,Collection<KnowledgePackage> packages) throws IOException, ClassNotFoundException {
		System.out.println("Processing file/folder : "+location);
		File pkgDir = new File(location);
		System.out.println("PKGS dir exists "+pkgDir.exists());
		if(!pkgDir.exists()){
//			ClassLoader loader = KnowledgePackageUtil.class.getClassLoader();
			
			URL url = KnowledgePackageUtil.class.getClassLoader().getResource(location);
			System.out.println("URL of location path for DRL pkgs "+url);
			if(url==null){
				System.err.println("Unable to find resource: "+location);
				return;
			}
			pkgDir = new File(url.getPath());
			if(!pkgDir.exists()) {
				System.err.println(" directory doesn't exist: "+pkgDir.getName());
				return;
			}
		}
		if(pkgDir.isDirectory()){
			File[] files = pkgDir.listFiles();
			for(File file : files){
				loadPackages(file.getAbsolutePath(), packages);
			}
		}else if(pkgDir.getName().endsWith(".pkg")){
			FileInputStream fis = new FileInputStream(pkgDir);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			KnowledgePackageImp imp = new KnowledgePackageImp();
			imp.readExternal(ois);
			
			KnowledgePackageAdapter kp = new KnowledgePackageAdapter(imp);
			packages.add(kp);
			
		}
		
 		
	}

}
