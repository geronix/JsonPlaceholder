package team.max.jsonplaceholder.user.mapping;


import java.util.ArrayList;
import java.util.List;

import team.max.jsonplaceholder.main.database.entity.Address;
import team.max.jsonplaceholder.main.database.entity.Company;
import team.max.jsonplaceholder.main.database.entity.User;
import team.max.jsonplaceholder.user.dto.AddressDTO;
import team.max.jsonplaceholder.user.dto.CompanyDTO;
import team.max.jsonplaceholder.user.dto.UserDTO;

public class UserMapper {

    public static List<User> mapperUserDTOToUser(List<UserDTO> dtos){
        List<User> users = new ArrayList<>();
        for (UserDTO dto : dtos){
            User user = new User();
            user.setServerUserId(dto.getId());
            user.setName(dto.getName());
            user.setUsername(dto.getUsername());
            user.setEmail(dto.getEmail());
            user.setPhone(dto.getPhone());
            user.setWebsite(dto.getWebsite());

            Address address = new Address();
            address.setStreet(dto.getAddress().getStreet());
            address.setSuite(dto.getAddress().getSuite());
            address.setCity(dto.getAddress().getCity());
            address.setZipcode(dto.getAddress().getZipcode());
            user.setAddress(address);

            Company company = new Company();
            company.setName(dto.getCompany().getName());
            company.setCatchPhrase(dto.getCompany().getCatchPhrase());
            company.setBs(dto.getCompany().getBs());
            user.setCompany(company);
            users.add(user);
        }
        return users;
    }

    public static List<UserDTO> mapperUserToUserDTO(List<User> users){
        List<UserDTO> dtos = new ArrayList<>();
        for (User user : users){
            UserDTO dto = new UserDTO();
            dto.setId(user.getServerUserId());
            dto.setName(user.getName());
            dto.setUsername(user.getUsername());
            dto.setEmail(user.getEmail());
            dto.setPhone(user.getPhone());
            dto.setWebsite(user.getWebsite());

            AddressDTO address = new AddressDTO();
            address.setStreet(user.getAddress().getStreet());
            address.setSuite(user.getAddress().getSuite());
            address.setCity(user.getAddress().getCity());
            address.setZipcode(user.getAddress().getZipcode());
            dto.setAddress(address);

            CompanyDTO company = new CompanyDTO();
            company.setName(user.getCompany().getName());
            company.setCatchPhrase(user.getCompany().getCatchPhrase());
            company.setBs(user.getCompany().getBs());
            dto.setCompany(company);

            dtos.add(dto);
        }
        return dtos;
    }
}
