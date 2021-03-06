package compare.site.service.rozetka.tablet;

import com.gargoylesoftware.htmlunit.WebClient;
import compare.site.dto.productSite.ProductSite;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.service.ResponseUploadForFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TabletRozetkaService {

    Page<TabletsRozetka> findAllPageUsingPageable(int page, int size);
    Page<TabletsRozetka> findAllByModelContains(String s, Pageable pageable);
    ResponseUploadForFactory saveToBase (ProductSite productSite, WebClient webClient);
    void deleteAllTablets ();

}
