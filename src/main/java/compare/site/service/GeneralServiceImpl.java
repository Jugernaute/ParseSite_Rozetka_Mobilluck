package compare.site.service;

import compare.site.dao.GeneralDao;
import compare.site.dao.rozetka.TabletsRozetkaDao;
import compare.site.dao.rozetka.TelephonesRozetkaDao;
import compare.site.entity.ProductAbstract;
import compare.site.entity.rozetka.TabletsRozetka;
import compare.site.entity.rozetka.TelephonesRozetka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class GeneralServiceImpl<T extends ProductAbstract> implements GeneralService<T> {
    @Autowired
    private GeneralDao<T> generalDao;
    @Autowired
    private TelephonesRozetkaDao telephonesRozetkaDao;
    @Autowired
    private TabletsRozetkaDao tabletsRozetkaDao;

    public T saveProduct(T t) {
        return generalDao.save(t);
    }

    @Override
    public List findAllProducts(Class<?> aClass) {
        if (aClass.equals(TelephonesRozetka.class)){
            return findAllTelephonesRozetka();
        }else if(aClass.equals(TabletsRozetka.class)){
            return findAllTabletsRozetka();
        }
        return null;
    }

    private List<TabletsRozetka> findAllTabletsRozetka (){
        return tabletsRozetkaDao.findAll();
    }

    private List<TelephonesRozetka> findAllTelephonesRozetka () {
        return telephonesRozetkaDao.findAll();
    }

    @Override
    public Page<T> findAllPageUsingPageable(int page, int size) {
        return generalDao.findAll(PageRequest.of(page, size));
    }

    @Override
    public Page<T> findAllByModelContains(String s, Pageable pageable) {
        try {
            return generalDao.findAllByModelContains(s, pageable);
        }
        catch (Exception e){
            System.out.println("error -> "+ e.getMessage());
        }
        return null;
    }

    @Override
    public T findByEnumProductsAndEnumSite(String site, String product) {
        return generalDao.findByEnumProductsAndEnumSite(site, product);
    }
}
