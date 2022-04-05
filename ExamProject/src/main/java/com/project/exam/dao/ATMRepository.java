package com.project.exam.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.exam.model.CustomerSignUp;

@Repository
public interface ATMRepository extends CrudRepository<CustomerSignUp, Integer>
{
	@Modifying
	@Query(value = "insert into CUSTOMER (NAME, CARD_NO, PIN, BALANCE, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIPCODE, CREATED_DT, UPDATED_DT) "
				+ "VALUES (:name, :cardNo, :amount, :tranType, :addr1, :addr2, :city, :state, :code, NOW(), NOW())", nativeQuery = true)
	void insertCustomer(@Param("name") String name, @Param("cardNo") String cardNo, @Param("amount")  String amount, @Param("tranType") String tranType, @Param("addr1") String addr1, 
			@Param("addr2") String addr2, @Param("city") String city, @Param("state") String state, @Param("code")  String code);
	
	@Query(value = "Select e.NAME from Customer e where e.CARD_NO=:cardNo and e.PIN=:pin", nativeQuery = true)
	String getCustomerName(@Param("cardNo") String cardNo, @Param("pin") String pin);

	@Query(value = "Select e.BALANCE from Customer e where e.CARD_NO=:cardNo", nativeQuery = true)
	String getAccountBalance(@Param("cardNo") String cardNo);
	
	@Modifying
	@Query(value = "insert into CUSTOMER_TRAN_DTL (CARD_NO, AMOUNT, TRAN_TYPE, CREATED_DT) VALUES (:cardNo, :amount, :tranType, NOW())", nativeQuery = true)
	void insertTransaction(@Param("cardNo") String cardNo, @Param("amount")  String amount, @Param("tranType") String tranType);

	@Modifying
	@Query(value = "update CUSTOMER set BALANCE=?2 where CARD_NO=?1", nativeQuery = true)
	void updateAccountBalance(@Param("cardNo") String cardNo, @Param("newBalance")  String newBalance);
}