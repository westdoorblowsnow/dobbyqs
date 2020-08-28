package dobby.dobbyqs.backstage.service;

import dobby.dobbyqs.backstage.bean.BackProfession;

import java.util.List;

public interface BackProfessionService {
    List<BackProfession> getAllBackProfessions();
    int updateBackProfession(BackProfession backProfession);
    int insertBackProfession(BackProfession backProfession);
}
