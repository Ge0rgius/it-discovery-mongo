package it.discovery.nosql.repository;

import it.discovery.mongo.model.Contact;
import it.discovery.mongo.model.Publisher;
import it.discovery.mongo.repository.PublisherRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PublisherRepositoryTest {

    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void findAll_returnsAllPublishers() {
        Publisher publisher = new Publisher();
        publisher.setName("Packt");
        Contact contact = new Contact();
        contact.setEmail("info@packt.com");
        contact.setPhone("1234567");
        publisher.setContact(contact);

//		publisherRepository.save(publisher);
//
//		List<Publisher> list = publisherRepository.findAll();
//		assertEquals(1, list.size());
//		assertEquals("Packt", list.get(0).getName());
//		assertEquals("1234567", list.get(0).getContact().getPhone());
//		assertTrue(list.get(0).getCreated().isBefore(LocalDateTime.now()));
    }

}
