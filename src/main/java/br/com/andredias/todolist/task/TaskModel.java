package br.com.andredias.todolist.task;


import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    /* 
     * Id
     * Usuário
     * Descrição
     * Título
     * Data Início
     * Data Término
     * 
     */
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String description;
    @Column(length = 50)
    private String title;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt; //yyyy-mm-dd HH:mm:ss

    private UUID idUser;

    public void setTitle(String t) throws Exception
    {
        if(t.length() > 50)
        {
            throw new Exception("O campo title deve conter no máximo 50 caracteres");
        }
        this.title = t;
    }
}
