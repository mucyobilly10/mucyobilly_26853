package auca.ac.rw.restfullApiAssignment.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import auca.ac.rw.restfullApiAssignment.modal.Product;
import auca.ac.rw.restfullApiAssignment.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepo; 

    public String saveProduct(Product product){

        Optional<Product> checkProduct = productRepo.findById(product.getId());
        
        if(checkProduct.isPresent()){
            return "Product with id "+ product.getId() + " already exists.";
        }else{
                 productRepo.save(product);
                 return "Product saved successfully.";
        }
       

    }

    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    public Optional<Product> getProductById(Long id){
        return productRepo.findById(id);
    }

    public String updateProduct(Long id, Product updated){
        Optional<Product> existing = productRepo.findById(id);

        if(existing.isPresent()){
            Product product = existing.get();
            product.setName(updated.getName());
            product.setDescription(updated.getDescription());
            product.setPrice(updated.getPrice());
            product.setCategory(updated.getCategory());
            product.setStockQuantity(updated.getStockQuantity());
            productRepo.save(product);
            return "Product updated successfully.";
        }else{
            return "Product with id "+ id + " not found.";
        }
    }

    public String deleteProduct(Long id){
        if(productRepo.existsById(id)){
            productRepo.deleteById(id);
            return "Product deleted successfully.";
        }else{
            return "Product with id "+ id + " not found.";
        }
    }
}
