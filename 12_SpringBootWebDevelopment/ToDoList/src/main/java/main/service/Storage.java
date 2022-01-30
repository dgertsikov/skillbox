package main.service;

import main.model.ToDo;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage
{
    private static AtomicInteger currentId = new AtomicInteger();
    private static ConcurrentHashMap<Integer, ToDo> toDos = new ConcurrentHashMap<>();

    public static int addToDo(ToDo toDo){
        currentId.incrementAndGet();
        int id = currentId.intValue();
        toDo.setId(id);
        toDos.put(id, toDo);

        return id;
    }

    public static List<ToDo> getAllToDos()
    {
        ArrayList<ToDo> toDoList = new ArrayList<>();
        toDoList.addAll(toDos.values());
        return toDoList;
    }

    public static ToDo getToDo(int id)
    {
        if(toDos.containsKey(id)){
            return toDos.get(id);
        }
        return null;
    }

    public static void deleteToDo(int id)
    {
        if(toDos.containsKey(id))
        {
            toDos.remove(id);
        }
    }

    public static void deleteAll()
    {
        Iterator<Integer> iterator = toDos.keySet().iterator();
        while (iterator.hasNext()){
            Integer keyValue = iterator.next();
            iterator.remove();
        }
    }

    public static ToDo editToDo(ToDo toDo)
    {
        int id = toDo.getId();
        if(toDos.containsKey(id))
        {
            toDos.get(id).setName(toDo.getName());
            toDos.get(id).setDateStart(toDo.getDateStart());
            toDos.get(id).setDateEnd(toDo.getDateEnd());
            toDos.get(id).setUser(toDo.getUser());
            return toDos.get(id);
        }
        return null;
    }

}
