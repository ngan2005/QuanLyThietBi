package com.example.QuanLyThietBi.repository;

import com.example.QuanLyThietBi.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Lấy tất cả nhân viên còn hiệu lực
    List<Employee> findByIsActiveTrue();

    // Tìm nhân viên theo code
    Optional<Employee> findByCode(String code);

    // Tìm nhân viên theo code và còn hiệu lực
    Optional<Employee> findByCodeAndIsActiveTrue(String code);

    // Tìm kiếm theo tên, không phân biệt hoa thường, chỉ trong nhân viên còn hiệu lực
    @Query("""
            select e 
            from Employee e
            left join e.dsThietBi ds
            where e.isActive=true 
                        and (
                       lower(e.tenNV) like lower(concat('%', :keyword, '%') ) 
                       or lower(e.chucVu) like lower(concat('%', : keyword, '%') )
                       or lower(ds.deviceTen) like lower(concat('%', :keyword, '%') )
                       or lower(ds.deviceLoai) like lower(concat('%', :keyword, '%') )
                       or lower(ds.code) like lower(concat('%', :keyword, '%') ) 
                       )  
            """)
    List<Employee> timTheoTen(@Param("keyword") String keyword);
}
