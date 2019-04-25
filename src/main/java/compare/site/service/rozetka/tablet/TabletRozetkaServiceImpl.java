package compare.site.service.rozetka.tablet;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import compare.site.dao.rozetka.TabletsRozetkaDao;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.service.ResponseUploadForFactory;
import compare.site.service.UploadProductAbstract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.net.MalformedURLException;
import java.util.List;

@Service
@Transactional
public class TabletRozetkaServiceImpl extends UploadProductAbstract implements TabletRozetkaService {

    @Autowired
    private TabletsRozetkaDao tabletsDao;

    public Page<TabletsRozetka> findAllPageUsingPageable(int page, int size) {
        return tabletsDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<TabletsRozetka> findAllByModelContains(String s, Pageable pageable) {
        return tabletsDao.findAllByModelContains(s, pageable);
    }

    /**
     * Creates connection to site and find page with product
     * than call methods which save this product
     * and date of upload to DB.
     * Calculate how much products are upload to DB
     * @return how much products are upload to DB and date of upload
     * */
    @Override
    public ResponseUploadForFactory saveToBase(ProductSite productSite, WebClient webClient) {
        /*clear all tablets from DB*/
        deleteAllTablets();
        try {
            /**
             * connection to page with tablets
             * @exception java.io.IOException if an IO problem occurs
             * @exception MalformedURLException if an error occurred when creating a URL object
             * */
            HtmlPage pageHome = webClient.getPage("https://rozetka.com.ua/tablets/c130309/filter/");
            /* find domElements which are responsible for how much pages are with tablets */
            List<HtmlSpan> listPages = pageHome.getByXPath("//span[@class='paginator-catalog-l-i-active hidden']");

            /** last index of {@link listPages} display max number of page */
            int numPages = Integer.parseInt(listPages.get(listPages.size()-1).asText());

            /**
             *Collection of tablets from each page and call method saving
             * for simplified  version here {<code>j=3</code>}
             * */
            for (int j = 1; j <= 3; j++) {
                /**
                 * url site where page = {<code>j</code>}
                 * */
                String http = "https://rozetka.com.ua/tablets/c130309/filter/page=" + String.valueOf(j);
                saveProduct(productSite, webClient, http);
            }
            webClient.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        /* clear the counter from LoadProductAbstract.class */
        nums=0;
        return dateOfUpdateService.responseUploadForFactory();
    }

    @Override
    public void deleteAllTablets() {
        tabletsDao.deleteAll();
    }
}
