package com.example.demo.votingplatform;
import com.example.demo.votingplatform.auth.model.UserGender;
import com.example.demo.votingplatform.auth.repository.UserGenderRepository;
import com.example.demo.votingplatform.campaign.model.CampaignType;
import com.example.demo.votingplatform.campaign.repository.CampaignTypeRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DataInsert {
    private final UserGenderRepository userGenderRepository;
    private final CampaignTypeRepository campaignTypeRepository;

    private void data_insert(){
        UserGender userGender1 = new UserGender();
        userGender1.setGender(UserGender.Gender.Male);
        userGenderRepository.save(userGender1);
        UserGender userGender2 = new UserGender();
        userGender2.setGender(UserGender.Gender.Female);
        userGenderRepository.save(userGender2);


        CampaignType campaignType1 = new CampaignType();
        campaignType1.setType(CampaignType.Type.Candidate);
        campaignTypeRepository.save(campaignType1);
        CampaignType campaignType2 = new CampaignType();
        campaignType2.setType(CampaignType.Type.Topic);
        campaignTypeRepository.save(campaignType2);

    }
}
