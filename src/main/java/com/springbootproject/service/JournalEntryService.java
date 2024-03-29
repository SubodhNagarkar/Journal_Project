package com.springbootproject.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import com.springbootproject.entity.JournalEntry;
import com.springbootproject.entity.User;
import com.springbootproject.repository.JournalEntryRepository;
import com.springbootproject.repository.UserRepository;
@Component
public class JournalEntryService {

	@Autowired
	private JournalEntryRepository journalEntryRepository;
	
	@Autowired
	private UserService userService;
//	@Transactional 
	public void saveEntry(JournalEntry journalEntry,String userName) {
		try {
			User user = userService.findByUsername(userName);
			JournalEntry saved = journalEntryRepository.save(journalEntry);
			user.getJournalEntries().add(saved);
			userService.saveUser(user);	
		}catch(Exception e) {
			System.out.println(e);
//			throw new RuntimeErrorException("Error occured", e);
		}
		
	}
	public void saveEntry2(JournalEntry journalEntry) {
		journalEntryRepository.save(journalEntry);
	}
	
	public List<JournalEntry> getall(){
		return journalEntryRepository.findAll();
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return journalEntryRepository.findById(id);
	}
	
	public void deleteByid(ObjectId id,String userName) {
		User user = userService.findByUsername(userName);
		user.getJournalEntries().removeIf(x -> x.getId().equals(id));
		userService.saveUser(user);
		 journalEntryRepository.deleteById(id);;
	}
}
