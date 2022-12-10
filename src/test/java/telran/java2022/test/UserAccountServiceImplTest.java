package telran.java2022.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.dto.UserAccountResponseDto;
import telran.java2022.accounting.dto.UserRegisterDto;
import telran.java2022.accounting.dto.UserUpdateDto;
import telran.java2022.accounting.model.UserAccount;
import telran.java2022.accounting.service.UserAccountService;
import telran.java2022.accounting.service.UserAccountServiceImpl;

@RequiredArgsConstructor
@SpringBootTest
class UserAccountServiceImplTest {
	final ModelMapper modelMapper;
	final UserAccountRepository repository;
	PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	UserAccountService service;
	@BeforeEach
	void setUp() throws Exception {
		service = new UserAccountServiceImpl(repository, modelMapper, passwordEncoder);
		UserAccount userAccount = new UserAccount("Test", "1234", "Test", "Testovitch");
		service.addUser(modelMapper.map(userAccount, UserRegisterDto.class));
	}

	@Test
	void testAddUser() {
		UserAccount userAccount = new UserAccount("Test", "1234", "Test", "Testovitch");
		assertNotNull(service.addUser(modelMapper.map(userAccount, UserRegisterDto.class)));
	}

	@Test
	void testGetUser() {
		UserAccount userAccount = new UserAccount("Test", "1234", "Test", "Testovitch");
		assertEquals(modelMapper.map(userAccount, UserAccountResponseDto.class), service.getUser(userAccount.getLogin()));
	}

	@Test
	void testRemoveUser() {
		UserAccount userAccount = new UserAccount("Test", "1234", "Test", "Testovitch");
		assertEquals(modelMapper.map(userAccount, UserAccountResponseDto.class), service.removeUser(userAccount.getLogin()));
	}

	@Test
	void testEditUser() {
		UserAccount userAccount = new UserAccount("Test", "1234", "Test", "Testovitch");
		assertEquals(modelMapper.map(userAccount, UserAccountResponseDto.class), service.editUser(userAccount.getLogin(), modelMapper.map(userAccount, UserUpdateDto.class)));
	}

	@Test
	void testChangeRolesList() {
		fail("Not yet implemented");
	}

	@Test
	void testChangePassword() {
		fail("Not yet implemented");
	}

}
