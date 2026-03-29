package main.java.logic;

import main.java.io.Accessor;
import main.java.ui.SlideViewerComponent;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * <p>main.java.logic.Presentation maintains the slides in the presentation.</p>
 * <p>There is only instance of this class.</p>
 * @author Ian F. Darwin, ian@darwinsys.com, Gert Florijn, Sylvia Stuurman
 * @version 1.1 2002/12/17 Gert Florijn
 * @version 1.2 2003/11/19 Sylvia Stuurman
 * @version 1.3 2004/08/17 Sylvia Stuurman
 * @version 1.4 2007/07/16 Sylvia Stuurman
 * @version 1.5 2010/03/03 Sylvia Stuurman
 * @version 1.6 2014/05/16 Sylvia Stuurman
 */

public class Presentation {
	private String showTitle; // title of the presentation
	private int currentSlideNumber = 0; // the slidenummer of the current main.java.logic.Slide
	private ArrayList<Slide> showList = null; // an ArrayList with Slides
	private ArrayList<PresentationObserver> observers;
	private Accessor accessor;

	public Presentation() {
		this.clear();
	}

	public int getSize() {
		return this.showList.size();
	}

	public String getTitle() {
		return this.showTitle;
	}

	public void setTitle(String nt) {
		this.showTitle = nt;
	}

	// give the number of the current slide
	public int getSlideNumber() {
		return this.currentSlideNumber;
	}

	// change the current slide number and signal it to the window
	public void setSlideNumber(int number) {
		this.currentSlideNumber = number;
		this.notifyObservers();
	}

	// go to the previous slide unless your at the beginning of the presentation
	public void prevSlide() {
		if (this.currentSlideNumber > 0) {
			this.setSlideNumber(this.currentSlideNumber - 1);
	    }
		this.notifyObservers();
	}

	// go to the next slide unless your at the end of the presentation.
	public void nextSlide() {
		if (this.currentSlideNumber < (this.showList.size()-1)) {
			this.setSlideNumber(this.currentSlideNumber + 1);
		}
		this.notifyObservers();
	}

	// Delete the presentation to be ready for the next one.
	public void clear() {
		this.showList = new ArrayList<Slide>();
		this.setSlideNumber(-1);
		this.notifyObservers();
	}

	// Add a slide to the presentation
	public void append(Slide slide) {
		this.showList.add(slide);
	}

	// Get a slide with a certain slidenumber
	public Slide getSlide(int number) {
		if (number < 0 || number >= getSize()){
			return null;
	    }
			return (Slide)this.showList.get(number);
	}

	// Give the current slide
	public Slide getCurrentSlide() {
		return this.getSlide(currentSlideNumber);
	}

	public void exit(int n) {
		System.exit(n);
	}

	public void addObserver(PresentationObserver observer)
	{
		this.observers.add(observer);
	}

	public void removeObserver(PresentationObserver observer)
	{
		Iterator<PresentationObserver> it = this.observers.iterator();
		while (it.hasNext())
		{
			PresentationObserver next =  it.next();
			if (next.equals(observer))
			{
				it.remove();
			}
		}
	}

	public void notifyObservers()
	{
		Slide data = this.getSlide(this.currentSlideNumber);
		for(PresentationObserver observer : this.observers)
		{
			observer.update(this, data);
		}
	}
}
