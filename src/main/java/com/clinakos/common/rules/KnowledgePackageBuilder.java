package com.clinakos.common.rules;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderConfiguration;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.compiler.PackageBuilderConfiguration;
import org.drools.core.definitions.impl.KnowledgePackageImp;
import org.drools.definition.KnowledgePackage;
import org.drools.impl.adapters.KnowledgeBuilderConfigurationAdapter;
import org.drools.impl.adapters.KnowledgePackageAdapter;
import org.drools.io.ResourceFactory;

public class KnowledgePackageBuilder {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Package builder started..."+args.length);
		System.out.println("Location of drl files args [0]"+args[0]);
		System.out.println("output folder location for DRL "+args[1]);
		if(args.length!=2){
			System.out.println("KnowledgePackageBuilder args: <rules-input-folder> <pkg-output-folder> ");
			System.exit(1);
		}
		
		List<File> drlFiles = new ArrayList<File>();
		
		loadDrls(args[0],drlFiles);
		buildDrls(drlFiles,args[1]);
	}

	private static void buildDrls(List<File> drlFiles,String outFolder) throws IOException {
		
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		
		for(File file : drlFiles){
			builder.add(ResourceFactory.newFileResource(file), ResourceType.DRL);
		}
		if ( builder.hasErrors() ) {
			Iterator<KnowledgeBuilderError> it = builder.getErrors().iterator();
			while(it.hasNext()){
				KnowledgeBuilderError err = it.next();
				System.err.println(err.getResource().getSourcePath() +" : " +err.getMessage());
			}
			System.err.println("ABORTING PACKAGE BUILD");
			System.exit(1);
		}
		
		File outDir = new File(outFolder);
		outDir.mkdirs();
		
		for(KnowledgePackage p : builder.getKnowledgePackages()){
			System.out.println("Writing package "+p.getName());
			KnowledgePackageImp pkg = null;
			if(p instanceof KnowledgePackageAdapter){
				pkg = (KnowledgePackageImp) ((KnowledgePackageAdapter) p).getDelegate();
			}else if(p instanceof KnowledgePackageImp){
				pkg = (KnowledgePackageImp) p;
			}
			File pkgFile = new File(outFolder+File.separator+pkg.getName()+".pkg");
			System.out.println("Deleting file "+pkgFile.getAbsolutePath());
			pkgFile.delete();
			System.out.println("Writing file "+pkgFile.getAbsolutePath());
			OutputStream os = new FileOutputStream(pkgFile);
			ObjectOutputStream oos = new ObjectOutputStream(os);
			pkg.writeExternal(oos);
			oos.close();
			os.close();
		}
		
		
	}

	private static void loadDrls(String location, List<File> drlFiles) {
		
		File dir = new File(location);
		System.out.println("Directory Location "+dir.getAbsolutePath());
		System.out.println("Directory exists "+dir.exists());
		if(!dir.exists()){
			System.out.println("Skipping "+dir.getName());
			return;
		}
		if(dir.isDirectory()){
			File[] files = dir.listFiles();
			for(File file : files){
				loadDrls(file.getAbsolutePath(), drlFiles);
			}
		}else if(dir.getName().endsWith(".drl")){
			System.out.println("Loaded "+dir.getName());
			drlFiles.add(dir);
		}
	}

}
