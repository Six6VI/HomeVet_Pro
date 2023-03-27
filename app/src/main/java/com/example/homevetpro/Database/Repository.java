package com.example.homevetpro.Database;

import android.app.Application;

import com.example.homevetpro.DAO.AnimalDAO;
import com.example.homevetpro.DAO.AppointmentDAO;
import com.example.homevetpro.DAO.CustomerDAO;
import com.example.homevetpro.DAO.ReportDAO;
import com.example.homevetpro.DAO.UserDAO;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.Entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private UserDAO mUserDAO;
    private CustomerDAO mCustomerDAO;
    private AnimalDAO mAnimalDAO;
    private AppointmentDAO mAppointmentDAO;
    private ReportDAO mReportDAO;
    private List<User> mAllUsers;
    private List<Customer> mAllCustomers;
    private List<Animal> mAllAnimals;
    private List<Appointment> mAllAppointments;
    private List<Report> mAllReports;
    private int mCustID;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        HomeVetDatabaseBuilder db=HomeVetDatabaseBuilder.getDatabase(application);
        mUserDAO=db.userDAO();
        mCustomerDAO=db.customerDAO();
        mAnimalDAO=db.animalDAO();
        mAppointmentDAO=db.appointmentDAO();
        mReportDAO=db.reportDAO();
    }
    public List<User> getmAllUsers(){
        databaseExecutor.execute(()-> {
            mAllUsers=mUserDAO.getAllUsers();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllUsers;
    }
    public void insert(User user){
        databaseExecutor.execute(()->{
            mUserDAO.insert(user);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(User user){
        databaseExecutor.execute(()->{
            mUserDAO.update(user);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(User user){
        databaseExecutor.execute(()->{
            mUserDAO.delete(user);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Customer> getmAllCustomers(){
        databaseExecutor.execute(()-> {
            mAllCustomers=mCustomerDAO.getAllCustomers();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllCustomers;
    }
    public void insert(Customer customer){
        databaseExecutor.execute(()->{
            mCustomerDAO.insert(customer);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Customer customer){
        databaseExecutor.execute(()->{
            mCustomerDAO.update(customer);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Customer customer){
        databaseExecutor.execute(()->{
            mCustomerDAO.delete(customer);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public int getmIDByName(String name){
        databaseExecutor.execute(()-> {
            mCustID=mCustomerDAO.getIDByName(name);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mCustID;

    }

    public List<Animal> getmAllAnimals(){
        databaseExecutor.execute(()-> {
            mAllAnimals=mAnimalDAO.getAllAnimals();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAnimals;
    }
    public void insert(Animal animal){
        databaseExecutor.execute(()->{
            mAnimalDAO.insert(animal);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Animal animal){
        databaseExecutor.execute(()->{
            mAnimalDAO.update(animal);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Animal animal){
        databaseExecutor.execute(()->{
            mAnimalDAO.delete(animal);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Appointment> getmAllAppointments(){
        databaseExecutor.execute(()->{
            mAllAppointments=mAppointmentDAO.getAllAppointments();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllAppointments;

    }

    public void insert(Appointment appointment){
        databaseExecutor.execute(()->{
            mAppointmentDAO.insert(appointment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Appointment appointment){
        databaseExecutor.execute(()->{
            mAppointmentDAO.update(appointment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Appointment appointment){
        databaseExecutor.execute(()->{
            mAppointmentDAO.delete(appointment);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<Report> getmAllReports(){
        databaseExecutor.execute(()->{
            mAllReports=mReportDAO.getAllReports();
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mAllReports;
    }
    public void insert(Report report){
        databaseExecutor.execute(()->{
            mReportDAO.insert(report);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void update(Report report){
        databaseExecutor.execute(()->{
            mReportDAO.update(report);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void delete(Report report){
        databaseExecutor.execute(()->{
            mReportDAO.delete(report);
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
