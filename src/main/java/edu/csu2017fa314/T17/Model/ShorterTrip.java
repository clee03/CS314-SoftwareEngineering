package edu.csu2017fa314.T17.Model;

import java.util.ArrayList;

public class ShorterTrip {
  int[][] mileageTable;
  ArrayList<Brewery> dests;

  public ShorterTrip(ArrayList<Brewery> destList) {
    this.dests = destList;
    this.mileageTable = new int[dests.size()][dests.size()];
    mileageTable = populateMileageTable();
  }

  public ArrayList<Brewery> computePath() {
    ArrayList<Brewery> pathList = new ArrayList<>();
    int min = Integer.MAX_VALUE;
    int startNode = 0;
    for (int j = 0; j < dests.size(); j++) {
      int k = pathDistance(this.pathFromAlpha(j));
      if (min > k) {
        min = k;
        startNode = j;
      }
    }
    ArrayList<Integer> pathFromA = pathFromAlpha(startNode);
    for (int i = 0; i < pathFromA.size(); i++) {
      pathList.add(dests.get(pathFromA.get(i)));
    }
    return pathList;
  }

  public ArrayList<Integer> pathFromAlpha(int startNode) {
    ArrayList<Integer> validNodes = new ArrayList<>();
    ArrayList<Integer> path = new ArrayList<>();
    int superStart = startNode;
    for (int i = 0; i < dests.size(); i++) {
      validNodes.add(i);
    }
    validNodes.remove(validNodes.indexOf(superStart));
    path.add(superStart);
    while (!validNodes.isEmpty()) {
      startNode = minIndex(startNode, validNodes);
      path.add(startNode);
    }
    path.add(superStart);
    return path;
  }

  public int minIndex(int startNode, ArrayList<Integer> validNodes) {
    int min = Integer.MAX_VALUE;
    int location = 0;
    for (int j = 0; j < mileageTable[startNode].length; j++) {
      if ((min > mileageTable[startNode][j]) && (validNodes.contains(j)) && (mileageTable[startNode][j] >0)) {
        min = mileageTable[startNode][j];
        location = j;
      }
    }
    validNodes.remove(validNodes.indexOf(location));
    return location;
  }

  public int[][] populateMileageTable() {
    Distance dist = new Distance();
    for (int i = 0; i < mileageTable.length; i++) {
      for (int j = 0; j < mileageTable[i].length; j++) {
        mileageTable[i][j] = dist.greatCircleDistance(dests.get(i), dests.get(j));
      }
    }
    return mileageTable;
  }

  public int pathDistance(ArrayList<Integer> path) {
    int ret = 0;
    for (int i = 0; i < path.size()-1; i++) {
      ret += mileageTable[path.get(i)][path.get(i + 1)];
    }
    return ret;
  }

  public int pathDistanceBrews(ArrayList<Brewery> path) {
    Distance dist = new Distance();
    int ret = 0;
    for (int i = 0; i < path.size()-1; i++) {
      ret += dist.greatCircleDistance(path.get(i), path.get(i+1));
    }
    return ret;
  }
}
