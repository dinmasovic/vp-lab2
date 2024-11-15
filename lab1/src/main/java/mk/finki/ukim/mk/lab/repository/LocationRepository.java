package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.model.Location;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class LocationRepository {
    private static final List<Location> lokacii= new ArrayList<>();

    static {
        lokacii.add(new Location("Grand Central Event Hall", "123 Main St, Springfield, NY 11234", "500", "A spacious and modern venue with state-of-the-art audio and visual equipment, ideal for conferences, weddings, and large corporate events."));
        lokacii.add(new Location("The Garden Lounge", "45 Greenway Ave, Portland, OR 97214", "100", "A cozy, indoor-outdoor space surrounded by lush plants, perfect for intimate gatherings, small parties, or private dinners with a natural atmosphere."));
        lokacii.add(new Location("Skyline Terrace", "789 High Point Rd, Los Angeles, CA 90028", "300", "This rooftop terrace offers stunning city views and is ideal for evening events, weddings, or any function that needs a picturesque backdrop."));
        lokacii.add(new Location("The Heritage Library", "222 Heritage Ln, Boston, MA 02114", "50", "A historic library with antique wooden shelves and cozy reading corners, suited for book readings, seminars, or workshops that require a quiet setting."));
        lokacii.add(new Location("Harborfront Conference Center", "555 Coastal Blvd, Miami, FL 33132", "800", "A large, upscale conference center located near the water, featuring spacious halls and breakout rooms, often used for business conventions, trade shows, and large presentations."));
    }
    public List<Location> findAll(){
        return lokacii;
    }
    public Location findByID(Long id){
        Location tmp=null;
        for(Location i:lokacii){
            if(i.getId().equals(id)){
                tmp= i;
                break;
            }
        }
        return tmp;
    }
}
