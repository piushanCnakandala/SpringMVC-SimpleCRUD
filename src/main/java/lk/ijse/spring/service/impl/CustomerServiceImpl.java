package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.CustomerDTO;
import lk.ijse.spring.entity.Customer;
import lk.ijse.spring.repo.CustomerRepo;
import lk.ijse.spring.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo repo;

    @Autowired
    private ModelMapper mapper;

    @Override
    public void saveCustomer(CustomerDTO dto){
        if (!repo.existsById(dto.getId())){
          /*  Customer customer =new Customer(dto.getId(), dto.getName(), dto.getAddress(), dto.getSalary());*/
         /*   Customer entity=mapper.map(dto,Customer.class);*/

            repo.save(mapper.map(dto,Customer.class));
        }else {
            throw new RuntimeException("Customer Already exist....");
        }

    }

    @Override
    public void deleteCustomer(String id){
        if (repo.existsById(id)) {
            repo.deleteById(id);
        }else {
            throw new RuntimeException("please Check the customer id");
        }


    }

    @Override
    public void updateCustomer(CustomerDTO dto){
        if (repo.existsById(dto.getId())) {
            repo.save(mapper.map(dto,Customer.class));
        }else {
          throw new RuntimeException("No Such Customer To Update");
        }
    }

    @Override
    public CustomerDTO searchCustomer(String id){

        if (repo.existsById(id)) {
            return mapper.map(repo.findById(id).get(),CustomerDTO.class) ;

        }else {
            throw new RuntimeException("No customer for "+id+" ...!");
        }


    }

    @Override
    public List<CustomerDTO> getAllCustomer(){
        return mapper.map(repo.findAll(),new TypeToken<List<CustomerDTO>>(){}.getType());
    }
}
