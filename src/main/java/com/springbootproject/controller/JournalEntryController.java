package com.springbootproject.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootproject.entity.JournalEntry;
import com.springbootproject.entity.User;
import com.springbootproject.service.JournalEntryService;
import com.springbootproject.service.UserService;
@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	@Autowired
	private JournalEntryService journalEntryService;
	
	@Autowired
	private UserService userService;
//	@PostMapping
//	public ResponseEntity< JournalEntry>  createEntry(@RequestBody JournalEntry journalEntry)
//	{
//		try {
//			journalEntry.setDate(LocalDateTime.now());
//			journalEntryService.saveEntry(journalEntry);
//			return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//	
//	}
	@PostMapping("/{userName}")
	public ResponseEntity< JournalEntry>  createEntry(@PathVariable String userName,@RequestBody JournalEntry journalEntry)
	{
		try {
			 
			journalEntry.setDate(LocalDateTime.now());
			journalEntryService.saveEntry(journalEntry,userName);
			return new ResponseEntity<>(journalEntry,HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
//	@GetMapping
//	public ResponseEntity<?> getall(){
//		  List<JournalEntry> getall = journalEntryService.getall();
//		  if(getall != null && !getall.isEmpty()) {
//			  return new ResponseEntity<>(getall,HttpStatus.OK);
//		  }
//		  return new ResponseEntity<>(HttpStatus.NOT_FOUND );
//	}
	@GetMapping("/{userName}")
	public ResponseEntity<?> getallJournalEntriesOfUser(@PathVariable String userName){
		 User user = userService.findByUsername(userName);
			List<JournalEntry> getall = user.getJournalEntries();
		  if(getall != null && !getall.isEmpty()) {
			  return new ResponseEntity<>(getall,HttpStatus.OK);
		  }
		  return new ResponseEntity<>(HttpStatus.NOT_FOUND );
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity< JournalEntry>  getJournalentryByid(@PathVariable  ObjectId id){
		 Optional<JournalEntry> journalEntry = journalEntryService.findById(id);
		 if(journalEntry.isPresent()) {
			 return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK );
		 }else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND );
		 }
	}
	
//	@DeleteMapping("/id/{id}")
//	public ResponseEntity<?> deleteJournalentryByid(@PathVariable  ObjectId id){
//		 journalEntryService.deleteByid(id);
//		  return new ResponseEntity<>(HttpStatus.NO_CONTENT );
//	}
	@DeleteMapping("/id/{userName}/{id}")
	public ResponseEntity<?> deleteJournalentryByid(@PathVariable  ObjectId id,@PathVariable  String userName){
		 journalEntryService.deleteByid(id,userName);
		  return new ResponseEntity<>(HttpStatus.NO_CONTENT );
	}
	
	@PutMapping("/id/{userName}/{id}")
	public ResponseEntity<?> updateJournalentryByid(@PathVariable String userName,
			@PathVariable  ObjectId id,
			@RequestBody JournalEntry newEntry){
		JournalEntry old= journalEntryService.findById(id).orElse(null);
		if(old!=null) {
			old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
			old.setContent(newEntry.getContent()!= null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
			journalEntryService.saveEntry(old,userName);
			 return new ResponseEntity<>(old,HttpStatus.OK );
		}
		
		 return new ResponseEntity<>(HttpStatus.NOT_FOUND );
		
	}
//	@PutMapping("/id/{id}")
//	public ResponseEntity<?> updateJournalentryByid(
//			@PathVariable  ObjectId id,@RequestBody JournalEntry newEntry){
//		JournalEntry old= journalEntryService.findById(id).orElse(null);
//		if(old!=null) {
//			old.setTitle(newEntry.getTitle()!= null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
//			old.setTitle(newEntry.getContent()!= null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
//			journalEntryService.saveEntry2(old);
//			 return new ResponseEntity<>(old,HttpStatus.OK );
//		}
//		
//		 return new ResponseEntity<>(HttpStatus.NOT_FOUND );
//		
//	}
}
