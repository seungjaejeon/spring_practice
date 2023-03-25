package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Member save(Member member) {
        member.setId(++sequence); //저장할때마다 id 1씩 증가시키며 저장
        store.put(member.getId(), member); //HashMap에 저장
        return member; //member return
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //id가 없을 경우 NULL이 출력되는데 이를 방지하기 위해 optional
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream() //name으로 찾기는 자바의 기능 사용
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());//리스트
    }
    public void clearStore(){
        store.clear();
    }
}
