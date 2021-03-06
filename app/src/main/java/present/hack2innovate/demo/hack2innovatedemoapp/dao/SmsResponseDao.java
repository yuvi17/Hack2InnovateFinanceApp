package present.hack2innovate.demo.hack2innovatedemoapp.dao;

import java.util.LinkedList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import present.hack2innovate.demo.hack2innovatedemoapp.models.SmsResponse;

/**
 * Created by anweshmishra on 11/11/17.
 */

public class SmsResponseDao implements BaseDao<SmsResponse>{
    private Realm realm;
    public SmsResponseDao(Realm realm) {
        this.realm = realm;
    }
    public List<SmsResponse> findAll() {
        List<SmsResponse> responses = new LinkedList<>();
        realm.beginTransaction();
        RealmResults<SmsResponse> results = realm.where(SmsResponse.class).findAll();
        realm.commitTransaction();
        for(SmsResponse smsResponse:results) {
            responses.add(smsResponse);
        }
        return responses;
    }
    public void create(SmsResponse smsResponse){
        realm.beginTransaction();
        realm.insert(smsResponse);
        realm.commitTransaction();
    }
    public RealmQuery<SmsResponse> getPersonalAccount(boolean personalAccount) {
        List<SmsResponse> responses = new LinkedList<>();
        RealmQuery<SmsResponse> queries = realm.where(SmsResponse.class).equalTo("personal_account",personalAccount);
        return queries;
    }
    public List<SmsResponse> getPersonalAccountTypes(boolean personalAccount) {
        RealmResults<SmsResponse> responses = getPersonalAccount(personalAccount).findAllSorted("type");
        return responses;
    }
}
