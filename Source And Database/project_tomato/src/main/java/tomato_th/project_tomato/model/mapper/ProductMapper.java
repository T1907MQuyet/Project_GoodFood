package tomato_th.project_tomato.model.mapper;



import org.mapstruct.Mapper;
import tomato_th.project_tomato.model.core.Product;
import tomato_th.project_tomato.model.dto.ProductDto;

import java.util.List;


public interface ProductMapper {
    ProductDto tpProductDTO(Product product);
    List<ProductDto> tpProductDTOs(List<Product> products);
    Product toProduct(ProductDto productDto);
}
