package compare.site.dao;

import compare.site.entity.DateOfUpdate;
import compare.site.entity.EnumProducts;
import compare.site.entity.EnumSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public interface DateOfUpdateDao extends JpaRepository<DateOfUpdate, Integer> {
    @Modifying //Modifying queries can only use void or int/Integer as return type!
    @Transactional
    @Query(value = "update parsing_site.date_of_update set date_of_update.date_time=:date where site =:site and product=:product", nativeQuery = true)
    void updateDateOfLoadProduct (@Param("date") String date,
                                  @Param("site") String site,
                                  @Param("product") String product);

//    @Query(value = "select parsing_site.date_of_update.date_time from parsing_site.date_of_update where site = :site and product = :product",nativeQuery = true)
//    DateOfUpdate findByEnumSiteAndEnumProducts(@Param("site") EnumSite site,
//                                               @Param("product") EnumProducts product);
    DateOfUpdate findByEnumSiteAndEnumProducts (EnumSite site, EnumProducts products);
}
