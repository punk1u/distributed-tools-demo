package tech.punklu.seataproduct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public void deduct(Long productId,Integer count){
        Optional<ProductEntity> byId = productDao.findById(productId);
        if (byId.isPresent()){
            ProductEntity entity = byId.get();
            entity.setCount(entity.getCount() - count);
            productDao.save(entity);
        }
    }
}
