package org.mambofish.spring.data.jsondb.repository.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

/**
 * @author vince
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes =JsonDBRepositoryConfig.class)
public class RepositoryTest {

    @Autowired private ContactRepository contactRepository;

    @Test
    public void shouldSaveContact() {
        Contact contact = contactRepository.save(new Contact("jane"));
        assertEquals("jane", contact.getId());
    }

    @Test
    public void shouldFindContact() {
        contactRepository.save(new Contact("jane"));
        Optional<Contact> contact = contactRepository.findById("jane");
        assertTrue(contact.isPresent());
        assertEquals("jane", contact.get().getId());
    }

    @Test
    public void shouldUpdateContact() {
        contactRepository.save(new Contact("jane"));

        Optional<Contact> contactOptional = contactRepository.findById("jane");
        assertTrue(contactOptional.isPresent());
        Contact contact = contactOptional.get();
        contact.setName("jennifer");
        contactRepository.save(contact);

        contactOptional = contactRepository.findById("jane");
        assertTrue(contactOptional.isPresent());
        contact = contactOptional.get();
        assertEquals("jennifer", contact.getName());
    }

    @Test
    public void shouldDeleteContact() {
        Contact contact = contactRepository.save(new Contact("jane"));
        contactRepository.delete(contact);
        assertFalse(contactRepository.existsById("jane"));
    }

    @Test
    public void shouldDeleteContactById() {
        Contact contact = contactRepository.save(new Contact("jane"));
        contactRepository.deleteById(contact.getId());
        assertFalse(contactRepository.existsById("jane"));
    }

    @Test
    public void shouldDeleteAll() {
        contactRepository.save(new Contact("jane"));
        contactRepository.save(new Contact( "pete"));

        assertTrue(contactRepository.existsById("jane"));
        assertTrue(contactRepository.existsById("pete"));

        contactRepository.deleteAll();

        assertFalse(contactRepository.existsById("jane"));
        assertFalse(contactRepository.existsById("pete"));
    }
}
