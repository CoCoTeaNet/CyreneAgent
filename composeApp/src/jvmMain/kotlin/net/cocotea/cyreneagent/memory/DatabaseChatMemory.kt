package net.cocotea.cyreneagent.memory

import com.agentsflex.core.memory.ChatMemory
import com.agentsflex.core.message.Message
import java.util.*

class DatabaseChatMemory : ChatMemory {

    private val id: Any = UUID.randomUUID().toString();

    override fun getMessages(): List<Message?> {
//        return Db.findList("select * from ....")
        return emptyList()
    }

    override fun addMessage(message: Message?) {
//        Db.save(message)
        println(message)
    }

    override fun id(): Any {
        return id
    }

    companion object {
        private const val serialVersionUID: Long = 6624929581768652065L
    }

}