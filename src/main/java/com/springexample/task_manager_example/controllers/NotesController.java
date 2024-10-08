package com.springexample.task_manager_example.controllers;

import com.springexample.task_manager_example.dto.CreateNoteDTO;
import com.springexample.task_manager_example.dto.CreateNoteResponseDTO;
import com.springexample.task_manager_example.entities.NoteEntity;
import com.springexample.task_manager_example.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {

    private final NoteService noteService;

    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") int taskId) {
        var notes = noteService.getNotesForTask(taskId);

        return ResponseEntity.ok(notes);
    }

    @PostMapping("")
    public ResponseEntity<CreateNoteResponseDTO> addNote(@PathVariable("taskId") int taskId, @RequestBody CreateNoteDTO body) {
        var note = noteService.addNoteForTask(taskId, body.getTitle(), body.getBody());
        return ResponseEntity.ok(new CreateNoteResponseDTO(taskId, note));
    }

}
