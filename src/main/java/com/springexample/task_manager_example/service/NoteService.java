package com.springexample.task_manager_example.service;

import com.springexample.task_manager_example.entities.NoteEntity;
import com.springexample.task_manager_example.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class NoteService {
    private TaskService taskService;
    private HashMap<Integer, TaskNoteHolder> taskNoteHolders = new HashMap<>();

    public NoteService(TaskService taskService) {
        this.taskService = taskService;
    }


    class TaskNoteHolder {
        protected int noteId = 1;
        protected ArrayList<NoteEntity> notes = new ArrayList<>();
    }

    public ArrayList<NoteEntity> getNotesForTask(int taskId) {
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }
        if(taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNoteHolder());
        }
        return taskNoteHolders.get(taskId).notes;
    }

    public NoteEntity addNoteForTask(int taskId, String title, String body) {
        TaskEntity task = taskService.getTaskById(taskId);
        if (task == null) {
            return null;
        }

        if(taskNoteHolders.get(taskId) == null) {
            taskNoteHolders.put(taskId, new TaskNoteHolder());
        }

        TaskNoteHolder taskNoteHolder = taskNoteHolders.get(taskId);
        NoteEntity note = new NoteEntity();
        note.setId(taskNoteHolder.noteId);
        note.setTitle(title);
        note.setBody(body);
        taskNoteHolder.notes.add(note);
        taskNoteHolder.noteId++;
        return note;
    }
}
