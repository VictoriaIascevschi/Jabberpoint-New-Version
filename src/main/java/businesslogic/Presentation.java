package businesslogic;

import io.Accessor;
import ui.SlideViewerComponent;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>main.java.logic.Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 *
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation
{
    private String showTitle; // title of the presentation
    private int currentSlideNumber = 0; // the slidenummer of the current main.java.logic.Slide
    private ArrayList<Slide> showList = null; // an ArrayList with Slides
    private ArrayList<PresentationObserver> observers;
    private Accessor accessor;

    public Presentation()
    {
        this.observers = new ArrayList<>();
        this.clear();
    }

    public int getSize()
    {
        return this.showList.size();
    }

    public String getTitle()
    {
        return this.showTitle;
    }

    public void setTitle(String nt)
    {
        this.showTitle = nt;
    }

    // give the number of the current slide
    public int getSlideNumber()
    {
        return this.currentSlideNumber;
    }

    // change the current slide number and signal it to the window
    public void setSlideNumber(int number)
    {
        this.currentSlideNumber = number;
        this.notifyObservers();
    }

    public ArrayList<PresentationObserver> getObservers()
    {
        return this.observers;
    }

    public void setObservers(ArrayList<PresentationObserver> observers)
    {
        this.observers = observers;
    }

    public ArrayList<Slide> getShowList()
    {
        return this.showList;
    }

    public void setShowList(ArrayList<Slide> showList)
    {
        this.showList = showList;
    }

    public void setShowTitle(String showTitle)
    {
        this.showTitle = showTitle;
    }

    public int getCurrentSlideNumber()
    {
        return this.currentSlideNumber;
    }

    public void setCurrentSlideNumber(int currentSlideNumber)
    {
        this.currentSlideNumber = currentSlideNumber;
    }

    public Accessor getAccessor()
    {
        return this.accessor;
    }

    public void setAccessor(Accessor accessor)
    {
        this.accessor = accessor;
    }

    public SlideViewerComponent getSlideViewComponent()
    {
        return this.slideViewComponent;
    }

    public void setSlideViewComponent(SlideViewerComponent slideViewComponent)
    {
        this.slideViewComponent = slideViewComponent;
    }

    // go to the previous slide unless your at the beginning of the presentation
    public void prevSlide()
    {
        if (this.currentSlideNumber > 0)
        {
            this.setSlideNumber(this.currentSlideNumber - 1);
        }
        
        this.notifyObservers();
    }

    // go to the next slide unless your at the end of the presentation.
    public void nextSlide()
    {
        if (this.currentSlideNumber < (this.showList.size() - 1))
        {
            this.setSlideNumber(this.currentSlideNumber + 1);
        }

        this.notifyObservers();
    }

    // Delete the presentation to be ready for the next one.
    public void clear()
    {
        this.showList = new ArrayList<Slide>();

        this.setSlideNumber(-1);
        this.notifyObservers();
    }

    // Add a slide to the presentation
    public void append(Slide slide)
    {
        this.showList.add(slide);
    }

    // Get a slide with a certain slidenumber
    public Slide getSlide(int number)
    {
        if (number < 0 || number >= getSize())
        {
            return null;
        }

        return (Slide) this.showList.get(number);
    }

    // Give the current slide
    public Slide getCurrentSlide()
    {
        return this.getSlide(currentSlideNumber);
    }

    public void exit(int status)
    {
        System.exit(status);
    }

    public void addObserver(PresentationObserver observer)
    {
        this.observers.add(observer);
    }

    public void removeObserver(PresentationObserver observer)
    {
        if (this.getObservers().size() == 1)
        {
            this.observers.remove(observer);
        }

        Iterator<PresentationObserver> it = this.observers.iterator();

        while (it.hasNext())
        {
            PresentationObserver next = it.next();

            if (next.equals(observer))
            {
                it.remove();
            }
        }
    }

    public void notifyObservers()
    {
        Slide data = this.getSlide(this.currentSlideNumber);

        for (PresentationObserver observer : this.observers)
        {
            observer.update(this, data);
        }
    }
}
