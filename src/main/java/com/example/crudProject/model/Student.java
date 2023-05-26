package com.example.crudProject.model;
import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Cloneable{
        @Id
        private String id;
        private String name;
        private String cinsiyet;
        private boolean mezuniyet;
        private String ogrenim;

        @Override
        public Object clone() throws CloneNotSupportedException
        {
                return super.clone();
        }

}
