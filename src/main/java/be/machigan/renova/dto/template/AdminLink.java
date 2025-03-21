package be.machigan.renova.dto.template;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminLink {
    public static final List<AdminLink> LINKS = List.of(
            new AdminLink("Gérer les utilisateurs", "/admin/users", "Ajouter ou supprimer des utilisateurs."),
            new AdminLink("Gérer mon compte", "/admin/account", "Modifier mes informations."),
            new AdminLink("Gérer les photos", "/admin/pictures", "Ajouter ou supprimer des photos."),
            new AdminLink("Gérer les promotions", "/admin/promos", "Ajouter, modifier ou supprimer des promotions")
    );

    private String name;
    private String link;
    private String description;
}
