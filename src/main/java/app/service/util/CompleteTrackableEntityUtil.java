package app.service.util;

import app.model.base.CompleteTrackableEntity;
import app.model.comparators.CompleteTrackableEntityByDateComparator;

import java.util.*;

public class CompleteTrackableEntityUtil {
    public static List<CompleteTrackableEntity> getMostRecentChangesList(List<List<CompleteTrackableEntity>> changesLists, int listSize) {
        int[] markers = new int[changesLists.size()];
        int[] listIndexes = new int[changesLists.size()];
        List<CompleteTrackableEntity> result = new ArrayList<>(listSize);
        List<CompleteTrackableEntity> listWithMostRecentEntities = new ArrayList<>();
        for (int i = 0; i < changesLists.size(); i++) {
            listWithMostRecentEntities.add(changesLists.get(i).get(0));
        }

        for (int i = 0; i < changesLists.size(); i++) {
            listIndexes[i] = i;
        }

        sortByMostRecentWithIndexes(listWithMostRecentEntities, listIndexes);

        int numberOfNotFullyUsedLists = changesLists.size();
        int listIndexWithMostRecentValueOnPrevStep = listIndexes[0];
        result.add(listWithMostRecentEntities.get(0));
        markers[listIndexWithMostRecentValueOnPrevStep] = 1;
        int newPos = changesLists.size() - 1;
        for (int i = 1; i < listSize; i++) {
            listIndexWithMostRecentValueOnPrevStep = listIndexes[0];
            if (changesLists.get(listIndexWithMostRecentValueOnPrevStep).size() > markers[listIndexWithMostRecentValueOnPrevStep])
            {
                for (int j = 1; j < numberOfNotFullyUsedLists; j++) {
                    if (new CompleteTrackableEntityByDateComparator().compare(
                            getMostRecentFromRemainder(listIndexes[j], changesLists, markers),
                            getMostRecentFromRemainder(listIndexWithMostRecentValueOnPrevStep, changesLists, markers)) < 0) {
//                    isWorst = false;
//                    firstLowerPos = j;
                        newPos = j - 1;
                        break;
                    }
                }
                for (int k = 0; k < newPos; k++) {
                    listIndexes[k] = listIndexes[k + 1];
                }
                listIndexes[newPos] = listIndexWithMostRecentValueOnPrevStep;
                result.add(getMostRecentFromRemainder(listIndexes[0], changesLists, markers));
                markers[listIndexes[0]]++;
            } else {
                for (int j = 0; j < numberOfNotFullyUsedLists - 1;  j++) {
                    listIndexes[j] = listIndexes[j + 1];
                }
                numberOfNotFullyUsedLists--;
            }
        }
        return result;
    }

    public static CompleteTrackableEntity getMostRecentFromRemainder(int index, List<List<CompleteTrackableEntity>> changesList, int[] markers) {
        return changesList.get(index).get(markers[index]);
    }

    public static void sortByMostRecentWithIndexes(List<CompleteTrackableEntity> list, int[] indexes) {
        sortByMostRecentWithIndexesRecursive(list, indexes, 0, indexes.length - 1);
    }

    private static void sortByMostRecentWithIndexesRecursive(List<CompleteTrackableEntity> list, int[] indexes, int first, int last) {
        int indexOfPivot = first;
        for (int i = first; i < last; i++) {
            if (list.get(i).getRelatedTimestamp().compareTo(
                    list.get(indexOfPivot).getRelatedTimestamp()
            ) < 0) {
                int tmp = indexes[i];
                indexes[i] = indexes[indexOfPivot];
                indexes[indexOfPivot] = tmp;
                list.add(i, list.get(indexOfPivot));
                list.remove(indexOfPivot);
                list.add(indexOfPivot, list.get(i));
                list.remove(i+1);
                indexOfPivot++;
            }
        }
        list.add(indexOfPivot, list.get(last));
        list.remove(last + 1);
        if (last - indexOfPivot > 1) {
            sortByMostRecentWithIndexesRecursive(list, indexes, indexOfPivot + 1, last);
            if (indexOfPivot - first > 1) {
                sortByMostRecentWithIndexesRecursive(list, indexes, first, indexOfPivot - 1);
            }
        }
    }
}
