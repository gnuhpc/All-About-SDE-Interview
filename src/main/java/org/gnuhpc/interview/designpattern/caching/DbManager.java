/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR Sample PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.gnuhpc.interview.designpattern.caching;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


/**
 *
 * <p>DBManager handles the communication with the underlying data store i.e. Database. It contains the
 * implemented methods for querying, inserting, and updating data. MongoDB was used as the database
 * for the application.</p>
 *
 * <p>Developer/Tester is able to choose whether the application should use MongoDB as its underlying
 * data storage (connect()) or a simple Java data structure to (temporarily) store the data/objects
 * during runtime (createVirtualDB()).</p>
 *
 */
public final class DbManager {
    private static Map<String, UserAccount> virtualDB;

    private DbManager() {
    }

    /**
     * Create DB
     */
    public static void createVirtualDb() {
        virtualDB = new HashMap<>();
    }

    /**
     * Connect to DB
     */
    public static void connect() throws ParseException {
    }

    /**
     * Read user account from DB
     */
    public static UserAccount readFromDb(String userId) {
        if (virtualDB.containsKey(userId)) {
            return virtualDB.get(userId);
        }
        return null;
    }

    /**
     * Write user account to DB
     */
    public static void writeToDb(UserAccount userAccount) {
        virtualDB.put(userAccount.getUserId(), userAccount);
    }

    /**
     * Update DB
     */
    public static void updateDb(UserAccount userAccount) {
        virtualDB.put(userAccount.getUserId(), userAccount);
    }

    /**
     *
     * Insert data into DB if it does not exist. Else, update it.
     */
    public static void upsertDb(UserAccount userAccount) {
        virtualDB.put(userAccount.getUserId(), userAccount);
    }
}
