/*
 * Copyright (c) 2010 Preemptive Labs / Andreas Bielk (http://preemptive.se)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package se.preemptive.redis.examples;

import se.preemptive.redis.RedisClient;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class SetGet
{
  public static void main(String[] args)
    throws TimeoutException
  {
    RedisClient client = new RedisClient("127.0.0.1", 6379);

    // set a value and wait for server response
    // set() returns a ResponseFuture
    client.set("mykey", "myvalue").block();

    // the future allows client to control timeouts
    Object value = client.
      get("mykey").
      withTimeout(1, TimeUnit.SECONDS);

    System.out.println("mykey = " + value);

    // and response type demarshalling and conversions
    String value2 = client.get("mykey").asString();

    System.out.println("mykey = " + value2);
  }
}