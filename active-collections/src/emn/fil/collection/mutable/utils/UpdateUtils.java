package emn.fil.collection.mutable.utils;

import java.util.Collections;

import emn.fil.collection.mutable.impl.AbstractCollection;
import emn.fil.collection.obs.event.EventCollectionAttribute;
import emn.fil.collection.obs.event.EventCollectionMessage;
import emn.fil.collection.obs.event.TypeEventEnum;
import emn.fil.collection.obs.type.OAbstract;

public class UpdateUtils<T extends OAbstract> {

	public static <T extends OAbstract> void updateCollection(AbstractCollection<T> collection, EventCollectionMessage<T> event) {
		switch (event.getEventCollection()) {
		case ADD:

			if (collection.getFunctionSelec() != null && !collection.getFunctionSelec().test(event.getElement()))
			{
				// Add element only if it's matching predicate
				break;
			}
			else if (collection.getFunctionApply() != null)
			{
				// Modify element to match the function before adding
				event.setElement(collection.getFunctionApply().apply(event.getElement()));
			}
			collection.add(event);
			break;
		case REMOVE:
			if (collection.getFunctionApply() != null)
			{
				// Modify element to match the function before adding
				event.setElement(collection.getFunctionApply().apply(event.getElement()));
			}
			collection.remove(event);
			break;
		default:
			break;
		}
	}

	public static <T extends OAbstract> void updateAttributeChanged(AbstractCollection<T> collection, EventCollectionAttribute<? extends OAbstract> event) {
		T element = (collection.getFunctionApply() != null) ? collection.getFunctionApply().apply(event.getElementBefore()) : event.getElementBefore();
		
		// Check if the element before modification was in this collection
		if (collection.getContent().contains(element))
		{
			if (collection.getFunctionSelec() != null)
			{
				// Then if the element still check the predicate, we update it
				if (collection.getFunctionSelec().test(event.getElementAfter()))
				{
					collection.getContent().set(collection.getContent().indexOf(event.getElementBefore()), event.getElementAfter());
					
					// Notify children
					collection.notifyAttributeChanged(event);
				}
				// else we delete it
				else
				{
					collection.remove(event.getElementBefore());
				}
			}
			// Modify element to match the function before updating
			else if (collection.getFunctionApply() != null)
			{
				collection.getContent().set(collection.getContent().indexOf(element), collection.getFunctionApply().apply(event.getElementAfter()));
				
				// Notify children
				collection.notifyAttributeChanged(event);
			}
			else if (collection.getFunctionSort() != null)
			{
				collection.getContent().remove(event.getElementBefore());
				collection.add(new EventCollectionMessage<T>(event.getElementAfter(), TypeEventEnum.ADD));
			} else {
				// case of union, intersection or difference
				collection.getContent().set(collection.getContent().indexOf(event.getElementBefore()), event.getElementAfter());
				
				// Notify children
				collection.notifyAttributeChanged(event);
			}
		}
		// If it is a new element we add it
		else
		{
			// The selection is the only one possible case for an add
			if (collection.getFunctionSelec() != null && collection.getFunctionSelec().test(event.getElementAfter()))
			{
				// Then if the element check the predicate, we add it
				collection.add(event.getElementAfter());
			}
		}
	}

}
