package com.example.phonebook.business.interactors

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.phonebook.dagger.scopes.ActivitiesScope
import com.example.phonebook.data.entities.Contact
import com.example.phonebook.data.pojo.ContactView
import com.example.phonebook.data.repos.ContactsRepoByRoom
import com.example.phonebook.data.repos.ServiceRepoShPref
import javax.inject.Inject

@ActivitiesScope
class MainInteractorImpl
@Inject constructor(
        private var mContactRepo: ContactsRepoByRoom,
        private var mServiceRepo: ServiceRepoShPref,
        private var mApplication: Application) : MainInteractor {

    private val mCurrentUser: String = mServiceRepo.getCurrentUser().orEmpty()
    private var mContactListView: ArrayList<ContactView> = mContactRepo.getContactList(mCurrentUser).map { it.toContactView() } as ArrayList<ContactView>

    override fun addContact(contact: ContactView): Boolean {
        when {
            contact.contactViewName.isEmpty() -> {
                Toast.makeText(mApplication, "Contact is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> mContactListView.add(contact)
        }
        saveToDB()
        return true
    }

    override fun updateContact(contact: ContactView): Boolean {
        when {
            contact.contactViewName.isEmpty() ->  {
                Toast.makeText(mApplication, "Contact is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else -> mContactListView[mContactListView.indexOf(contact)] = contact
        }
        saveToDB()
        return true
    }

    override fun deleteContact(contact: ContactView) {
        mContactListView.remove(contact)
        saveToDB()
    }

    override fun getContactList(): LiveData<ArrayList<ContactView>> {
        return Transformations.map(mContactRepo.getLiveContactList(mCurrentUser)) { it ->
            it?.map { it.toContactView() } as ArrayList<ContactView>
        }
    }

    override fun logOut() = mServiceRepo.setSignedUp(false)

    override fun save() = saveToDB()

    private fun Contact.toContactView() = ContactView (
            contactViewID = contactID,
            contactViewName = contactName,
            contactViewPhone = contactPhone,
            contactViewEmail = contactEmail,
            contactViewAddress = contactAddress
    )

    private fun ContactView.toContact() = Contact(
            contactID = contactViewID,
            contactName = contactViewName,
            contactPhone = contactViewPhone,
            contactEmail = contactViewEmail,
            contactAddress = contactViewAddress
    )

    private fun saveToDB() = mContactRepo.saveContactList(mCurrentUser, mContactListView.map { it.toContact() } as ArrayList<Contact>)
}