package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class SiteDao {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("DB_XML");
	EntityManager em = null;
	
	public Site findSite(int siteId){
		Site site = null;
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		site = em.find(Site.class, siteId);
		
		em.getTransaction().commit();
		em.close();
		return site;
	}
	
	public List<Site> findAllSites(){
		List<Site> sites = new ArrayList<Site>();
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		Query query = em.createNamedQuery("Site.findAll");
		sites = query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return sites;
	}
	
	public void exportSiteDatabaseToXmlFile(SiteDatabase database,String xmlFileName){
		File xmlFile = new File(xmlFileName);
		try {
			JAXBContext jaxb = JAXBContext.newInstance(SiteDatabase.class);
			Marshaller marshaller = jaxb.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(database, xmlFile);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void convertXmlFileToOutputFile(String inputXmlFileName, String outputXmlFileName, String xsltFileName){
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputXmlFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource source = new StreamSource(inputXmlFile);
		StreamSource xslt = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(source, output);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args){
		SiteDao siteDao = new SiteDao();
		List<Site> sites = siteDao.findAllSites();
		for(Site site:sites){
			System.out.println(site.getName());
		}
		
		SiteDatabase siteDatabse = new SiteDatabase();
		siteDatabse.setSites(sites);
		
		siteDao.exportSiteDatabaseToXmlFile(siteDatabse, "xml/sites.xml");
		
		siteDao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/site2html.xslt");
		siteDao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/site2equipment.xslt");
	}
}
