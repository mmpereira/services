import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.genebio.nextprot.service.PublicationService;

/**
 * 
 * @author dteixeira
 *
 */
public class PublicationTest  {
	
	@Autowired
	private PublicationService publicationService;

    @Test
    public void testGetById() {
    	//publicationService.getPublicationById(0);
    }

}
