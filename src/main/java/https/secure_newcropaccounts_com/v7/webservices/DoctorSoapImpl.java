
/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

package https.secure_newcropaccounts_com.v7.webservices;

import java.util.logging.Logger;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.3
 * 2013-10-25T09:49:17.872+05:30
 * Generated source version: 2.7.3
 * 
 */

@javax.jws.WebService(
                      serviceName = "Doctor",
                      portName = "DoctorSoap12",
                      targetNamespace = "https://secure.newcropaccounts.com/V7/webservices",
                      wsdlLocation = "https://preproduction.newcropaccounts.com/v7/WebServices/Doctor.asmx?wsdl",
                      endpointInterface = "https.secure_newcropaccounts_com.v7.webservices.DoctorSoap")
                      
public class DoctorSoapImpl implements DoctorSoap {

    private static final Logger LOG = Logger.getLogger(DoctorSoapImpl.class.getName());

    /* (non-Javadoc)
     * @see https.secure_newcropaccounts_com.v7.webservices.DoctorSoap#allLicensedPrescribersStatus(https.secure_newcropaccounts_com.v7.webservices.Credentials  credentials ,)https.secure_newcropaccounts_com.v7.webservices.AccountRequest  accountRequest )*
     */
    public https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetailResult allLicensedPrescribersStatus(https.secure_newcropaccounts_com.v7.webservices.Credentials credentials,https.secure_newcropaccounts_com.v7.webservices.AccountRequest accountRequest) { 
        LOG.info("Executing operation allLicensedPrescribersStatus");
        System.out.println(credentials);
        System.out.println(accountRequest);
        try {
            https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetailResult _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see https.secure_newcropaccounts_com.v7.webservices.DoctorSoap#registerLicensedPrescriberIdentifier(https.secure_newcropaccounts_com.v7.webservices.Credentials  credentials ,)https.secure_newcropaccounts_com.v7.webservices.AccountRequest  accountRequest ,)java.lang.String  identifier ,)java.lang.String  identifierType ,)java.lang.String  status ,)java.lang.String  expirationDate )*
     */
    public https.secure_newcropaccounts_com.v7.webservices.Result registerLicensedPrescriberIdentifier(https.secure_newcropaccounts_com.v7.webservices.Credentials credentials,https.secure_newcropaccounts_com.v7.webservices.AccountRequest accountRequest,java.lang.String identifier,java.lang.String identifierType,java.lang.String status,java.lang.String expirationDate) { 
        LOG.info("Executing operation registerLicensedPrescriberIdentifier");
        System.out.println(credentials);
        System.out.println(accountRequest);
        System.out.println(identifier);
        System.out.println(identifierType);
        System.out.println(status);
        System.out.println(expirationDate);
        try {
            https.secure_newcropaccounts_com.v7.webservices.Result _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see https.secure_newcropaccounts_com.v7.webservices.DoctorSoap#licensedPrescriberStatus(https.secure_newcropaccounts_com.v7.webservices.Credentials  credentials ,)https.secure_newcropaccounts_com.v7.webservices.AccountRequest  accountRequest ,)java.lang.String  licensedPrescriberID ,)java.lang.String  locationID )*
     */
    public https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetailResult licensedPrescriberStatus(https.secure_newcropaccounts_com.v7.webservices.Credentials credentials,https.secure_newcropaccounts_com.v7.webservices.AccountRequest accountRequest,java.lang.String licensedPrescriberID,java.lang.String locationID) { 
        LOG.info("Executing operation licensedPrescriberStatus");
        System.out.println(credentials);
        System.out.println(accountRequest);
        System.out.println(licensedPrescriberID);
        System.out.println(locationID);
        try {
            https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetailResult _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

    /* (non-Javadoc)
     * @see https.secure_newcropaccounts_com.v7.webservices.DoctorSoap#registerLicensedPrescriber(https.secure_newcropaccounts_com.v7.webservices.Credentials  credentials ,)https.secure_newcropaccounts_com.v7.webservices.AccountRequest  accountRequest ,)java.lang.String  xmlIn )*
     */
    public https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetailResult registerLicensedPrescriber(https.secure_newcropaccounts_com.v7.webservices.Credentials credentials,https.secure_newcropaccounts_com.v7.webservices.AccountRequest accountRequest,java.lang.String xmlIn) { 
        LOG.info("Executing operation registerLicensedPrescriber");
        System.out.println(credentials);
        System.out.println(accountRequest);
        System.out.println(xmlIn);
        try {
            https.secure_newcropaccounts_com.v7.webservices.RegisterPrescriberDetailResult _return = null;
            return _return;
        } catch (java.lang.Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }

}
