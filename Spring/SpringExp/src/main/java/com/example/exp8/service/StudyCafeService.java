package com.example.exp8.service;
import com.example.exp8.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SessionScope
@Service
public class StudyCafeService {

    private static final ArrayList<Integer> reservedSeat = new ArrayList<>();

    public StudyCafeService() {
        System.out.println("StudyCafeService Session Scope Instance Created: " + this.hashCode());
    }
    public String reserveSeat(int seatNum, User user){
        System.out.println("StudyCafeService.reserveSeat");
        if(reservedSeat.contains(seatNum)){
            return "이미 예약된 자리입니다. 다시 시도해 주세요";
        }else{
            reservedSeat.add(seatNum);
            user.getSeats().add(seatNum);
            return seatNum + "번 자리가 예약 되었습니다.";
        }
    }
    public String useSeat(User user, int seatNum){
        System.out.println("StudyCafeService.useSeat");
        LocalDateTime endTime = LocalDateTime.now().plusHours(2);
        user.setEndDate(endTime);
        user.setCurrentSeat(seatNum);
        return endTime + "까지 사용이 가능합니다";
    }
    public String deleteReserve(User user){
        System.out.println("StudyCafeService.deleteReserve");
        reservedSeat.remove(Integer.valueOf(user.getCurrentSeat()));
        user.getSeats().remove(Integer.valueOf(user.getCurrentSeat()));
        user.setEndDate(LocalDateTime.now());

        return user.getCurrentSeat() + "번 자리가 종료되었습니다.";
    }
}
