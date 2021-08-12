package project.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "files")
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @EqualsAndHashCode.Exclude
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "size")
    @EqualsAndHashCode.Exclude
    private Float size;

    @Enumerated(value = EnumType.STRING)
    @EqualsAndHashCode.Exclude
    private FileStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name = "created")
    @EqualsAndHashCode.Exclude
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    @Column(name = "last_modified")
    @EqualsAndHashCode.Exclude
    private Date lastModified;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    public File(String path) {
        java.io.File file = new java.io.File(path);
        try {
            this.size = (float) Files.size(file.toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        String[] f = file.getName().split("\\.");
        this.name = f[0];
        this.type = f[1];
        this.status = FileStatus.ACTIVE;
    }

    @EqualsAndHashCode.Include
    public String getFileName(){
        return name + "." + type;
    }
}
