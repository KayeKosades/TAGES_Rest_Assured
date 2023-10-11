package api.petstore.pojo.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Pet {
    private Long id;
    private Category category;
    private String name;
    private List<String> photoUrls;
    private List<Tag> tags;
    private String status;
    //
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Pet pet = (Pet) o;
//        return id.equals(pet.id) && category.equals(pet.category) && name.equals(pet.name) && photoUrls.equals(pet.photoUrls) && tags.equals(pet.tags) && status.equals(pet.status);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, category, name, photoUrls, tags, status);
//    }
//

//
//    public List<Tag> getTags() {
//        return tags;
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public Category getCategory() {
//        return category;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public List<String> getPhotoUrls() {
//        return photoUrls;
//    }
//
//    public String getStatus() { return status; }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public void setCategory(Category category) {
//        this.category = category;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPhotoUrls(List<String> photoUrls) {
//        this.photoUrls = photoUrls;
//    }
//
//    public void setTags(List<Tag> tags) {
//        this.tags = tags;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
}

