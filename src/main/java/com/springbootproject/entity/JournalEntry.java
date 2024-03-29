package com.springbootproject.entity;

import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "journal_entries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class JournalEntry {
	@Id
	private ObjectId id;
	@NonNull
	private String title;
	private String content;
	private LocalDateTime date;
}
