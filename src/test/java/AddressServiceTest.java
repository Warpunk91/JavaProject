import co.com.ps.c24a.entity.Address;
import co.com.ps.c24a.repository.AddressRepository;
import co.com.ps.c24a.service.AddressServicelpm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AddressServiceTest {

    @Mock
    private AddressRepository addressRepository;

    @InjectMocks
    private AddressServicelpm addressServicelpm;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllAddress() {
        Iterable<Address> addresses = List.of(new Address(), new Address());
        when(addressRepository.findAll()).thenReturn(addresses);

        Iterable<Address> result = addressServicelpm.getAddressAll();
        assertNotNull(result);
        verify(addressRepository, times(1)).findAll();
    }

    @Test
    void testGetAddressById() {
        Address address = new Address();
        address.setId(1L);
        when(addressRepository.findById(1L)).thenReturn(Optional.of(address));

        Optional<Address> result = addressServicelpm.getAddressById(1L);
        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());


        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testGetAddressById_NotFound() {
        when(addressRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> addressServicelpm.getAddressById(1L));
        verify(addressRepository, times(1)).findById(1L);
    }

    @Test
    void testSaveAddress() {
        Address address = new Address();
        address.setId(1L);
        when(addressRepository.save(any(Address.class))).thenReturn(address);

        Address result = addressServicelpm.saveAddress(address);
        assertNotNull(result);
        assertEquals(1L, result.getId());

        verify(addressRepository, times(1)).save(address);
    }

    @Test
    void testDeleteAddress() {
        when(addressRepository.existsById(1L)).thenReturn(true);
        doNothing().when(addressRepository).deleteById(1L);

        addressServicelpm.deleteAddress(1L);
        verify(addressRepository, times(1)).existsById(1L);
        verify(addressRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePerson_NotFound() {
        when(addressRepository.existsById(1L)).thenReturn(false);

        assertThrows(RuntimeException.class, () -> addressServicelpm.deleteAddress(1L));
        verify(addressRepository, times(1)).existsById(1L);
        verify(addressRepository, never()).deleteById(1L);
    }
}