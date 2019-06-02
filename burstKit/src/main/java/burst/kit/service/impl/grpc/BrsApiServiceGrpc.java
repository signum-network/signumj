package burst.kit.service.impl.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.21.0)",
    comments = "Source: brsApi.proto")
public final class BrsApiServiceGrpc {

  private BrsApiServiceGrpc() {}

  public static final String SERVICE_NAME = "BrsApiService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
      burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> getBroadcastTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BroadcastTransaction",
      requestType = burst.kit.service.impl.grpc.BrsApi.BasicTransaction.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
      burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> getBroadcastTransactionMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction, burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> getBroadcastTransactionMethod;
    if ((getBroadcastTransactionMethod = BrsApiServiceGrpc.getBroadcastTransactionMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getBroadcastTransactionMethod = BrsApiServiceGrpc.getBroadcastTransactionMethod) == null) {
          BrsApiServiceGrpc.getBroadcastTransactionMethod = getBroadcastTransactionMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.BasicTransaction, burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "BroadcastTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.BasicTransaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("BroadcastTransaction"))
                  .build();
          }
        }
     }
     return getBroadcastTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.TransactionBytes,
      burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> getBroadcastTransactionBytesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "BroadcastTransactionBytes",
      requestType = burst.kit.service.impl.grpc.BrsApi.TransactionBytes.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.TransactionBytes,
      burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> getBroadcastTransactionBytesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.TransactionBytes, burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> getBroadcastTransactionBytesMethod;
    if ((getBroadcastTransactionBytesMethod = BrsApiServiceGrpc.getBroadcastTransactionBytesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getBroadcastTransactionBytesMethod = BrsApiServiceGrpc.getBroadcastTransactionBytesMethod) == null) {
          BrsApiServiceGrpc.getBroadcastTransactionBytesMethod = getBroadcastTransactionBytesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.TransactionBytes, burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "BroadcastTransactionBytes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.TransactionBytes.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("BroadcastTransactionBytes"))
                  .build();
          }
        }
     }
     return getBroadcastTransactionBytesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
      burst.kit.service.impl.grpc.BrsApi.BasicTransaction> getCompleteBasicTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CompleteBasicTransaction",
      requestType = burst.kit.service.impl.grpc.BrsApi.BasicTransaction.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.BasicTransaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
      burst.kit.service.impl.grpc.BrsApi.BasicTransaction> getCompleteBasicTransactionMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction, burst.kit.service.impl.grpc.BrsApi.BasicTransaction> getCompleteBasicTransactionMethod;
    if ((getCompleteBasicTransactionMethod = BrsApiServiceGrpc.getCompleteBasicTransactionMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getCompleteBasicTransactionMethod = BrsApiServiceGrpc.getCompleteBasicTransactionMethod) == null) {
          BrsApiServiceGrpc.getCompleteBasicTransactionMethod = getCompleteBasicTransactionMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.BasicTransaction, burst.kit.service.impl.grpc.BrsApi.BasicTransaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "CompleteBasicTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.BasicTransaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.BasicTransaction.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("CompleteBasicTransaction"))
                  .build();
          }
        }
     }
     return getCompleteBasicTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Account> getGetAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccount",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Account.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Account> getGetAccountMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Account> getGetAccountMethod;
    if ((getGetAccountMethod = BrsApiServiceGrpc.getGetAccountMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountMethod = BrsApiServiceGrpc.getGetAccountMethod) == null) {
          BrsApiServiceGrpc.getGetAccountMethod = getGetAccountMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Account>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Account.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccount"))
                  .build();
          }
        }
     }
     return getGetAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.AccountATs> getGetAccountATsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountATs",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.AccountATs.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.AccountATs> getGetAccountATsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.AccountATs> getGetAccountATsMethod;
    if ((getGetAccountATsMethod = BrsApiServiceGrpc.getGetAccountATsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountATsMethod = BrsApiServiceGrpc.getGetAccountATsMethod) == null) {
          BrsApiServiceGrpc.getGetAccountATsMethod = getGetAccountATsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.AccountATs>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccountATs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.AccountATs.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccountATs"))
                  .build();
          }
        }
     }
     return getGetAccountATsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest,
      burst.kit.service.impl.grpc.BrsApi.Blocks> getGetAccountBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountBlocks",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Blocks.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest,
      burst.kit.service.impl.grpc.BrsApi.Blocks> getGetAccountBlocksMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest, burst.kit.service.impl.grpc.BrsApi.Blocks> getGetAccountBlocksMethod;
    if ((getGetAccountBlocksMethod = BrsApiServiceGrpc.getGetAccountBlocksMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountBlocksMethod = BrsApiServiceGrpc.getGetAccountBlocksMethod) == null) {
          BrsApiServiceGrpc.getGetAccountBlocksMethod = getGetAccountBlocksMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest, burst.kit.service.impl.grpc.BrsApi.Blocks>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccountBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Blocks.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccountBlocks"))
                  .build();
          }
        }
     }
     return getGetAccountBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest,
      burst.kit.service.impl.grpc.BrsApi.Orders> getGetAccountCurrentOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountCurrentOrders",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Orders.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest,
      burst.kit.service.impl.grpc.BrsApi.Orders> getGetAccountCurrentOrdersMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest, burst.kit.service.impl.grpc.BrsApi.Orders> getGetAccountCurrentOrdersMethod;
    if ((getGetAccountCurrentOrdersMethod = BrsApiServiceGrpc.getGetAccountCurrentOrdersMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountCurrentOrdersMethod = BrsApiServiceGrpc.getGetAccountCurrentOrdersMethod) == null) {
          BrsApiServiceGrpc.getGetAccountCurrentOrdersMethod = getGetAccountCurrentOrdersMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest, burst.kit.service.impl.grpc.BrsApi.Orders>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccountCurrentOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Orders.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccountCurrentOrders"))
                  .build();
          }
        }
     }
     return getGetAccountCurrentOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.EscrowTransactions> getGetAccountEscrowTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountEscrowTransactions",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.EscrowTransactions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.EscrowTransactions> getGetAccountEscrowTransactionsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.EscrowTransactions> getGetAccountEscrowTransactionsMethod;
    if ((getGetAccountEscrowTransactionsMethod = BrsApiServiceGrpc.getGetAccountEscrowTransactionsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountEscrowTransactionsMethod = BrsApiServiceGrpc.getGetAccountEscrowTransactionsMethod) == null) {
          BrsApiServiceGrpc.getGetAccountEscrowTransactionsMethod = getGetAccountEscrowTransactionsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.EscrowTransactions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccountEscrowTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.EscrowTransactions.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccountEscrowTransactions"))
                  .build();
          }
        }
     }
     return getGetAccountEscrowTransactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest,
      burst.kit.service.impl.grpc.BrsApi.Accounts> getGetAccountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccounts",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Accounts.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest,
      burst.kit.service.impl.grpc.BrsApi.Accounts> getGetAccountsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest, burst.kit.service.impl.grpc.BrsApi.Accounts> getGetAccountsMethod;
    if ((getGetAccountsMethod = BrsApiServiceGrpc.getGetAccountsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountsMethod = BrsApiServiceGrpc.getGetAccountsMethod) == null) {
          BrsApiServiceGrpc.getGetAccountsMethod = getGetAccountsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest, burst.kit.service.impl.grpc.BrsApi.Accounts>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Accounts.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccounts"))
                  .build();
          }
        }
     }
     return getGetAccountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Subscriptions> getGetAccountSubscriptionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountSubscriptions",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Subscriptions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Subscriptions> getGetAccountSubscriptionsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Subscriptions> getGetAccountSubscriptionsMethod;
    if ((getGetAccountSubscriptionsMethod = BrsApiServiceGrpc.getGetAccountSubscriptionsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountSubscriptionsMethod = BrsApiServiceGrpc.getGetAccountSubscriptionsMethod) == null) {
          BrsApiServiceGrpc.getGetAccountSubscriptionsMethod = getGetAccountSubscriptionsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Subscriptions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccountSubscriptions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Subscriptions.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccountSubscriptions"))
                  .build();
          }
        }
     }
     return getGetAccountSubscriptionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest,
      burst.kit.service.impl.grpc.BrsApi.Transactions> getGetAccountTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountTransactions",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Transactions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest,
      burst.kit.service.impl.grpc.BrsApi.Transactions> getGetAccountTransactionsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest, burst.kit.service.impl.grpc.BrsApi.Transactions> getGetAccountTransactionsMethod;
    if ((getGetAccountTransactionsMethod = BrsApiServiceGrpc.getGetAccountTransactionsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAccountTransactionsMethod = BrsApiServiceGrpc.getGetAccountTransactionsMethod) == null) {
          BrsApiServiceGrpc.getGetAccountTransactionsMethod = getGetAccountTransactionsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest, burst.kit.service.impl.grpc.BrsApi.Transactions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAccountTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Transactions.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAccountTransactions"))
                  .build();
          }
        }
     }
     return getGetAccountTransactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAliasRequest,
      burst.kit.service.impl.grpc.BrsApi.Alias> getGetAliasMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAlias",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAliasRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Alias.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAliasRequest,
      burst.kit.service.impl.grpc.BrsApi.Alias> getGetAliasMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAliasRequest, burst.kit.service.impl.grpc.BrsApi.Alias> getGetAliasMethod;
    if ((getGetAliasMethod = BrsApiServiceGrpc.getGetAliasMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAliasMethod = BrsApiServiceGrpc.getGetAliasMethod) == null) {
          BrsApiServiceGrpc.getGetAliasMethod = getGetAliasMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAliasRequest, burst.kit.service.impl.grpc.BrsApi.Alias>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAlias"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAliasRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Alias.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAlias"))
                  .build();
          }
        }
     }
     return getGetAliasMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest,
      burst.kit.service.impl.grpc.BrsApi.Aliases> getGetAliasesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAliases",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Aliases.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest,
      burst.kit.service.impl.grpc.BrsApi.Aliases> getGetAliasesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest, burst.kit.service.impl.grpc.BrsApi.Aliases> getGetAliasesMethod;
    if ((getGetAliasesMethod = BrsApiServiceGrpc.getGetAliasesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAliasesMethod = BrsApiServiceGrpc.getGetAliasesMethod) == null) {
          BrsApiServiceGrpc.getGetAliasesMethod = getGetAliasesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest, burst.kit.service.impl.grpc.BrsApi.Aliases>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAliases"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Aliases.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAliases"))
                  .build();
          }
        }
     }
     return getGetAliasesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.Asset> getGetAssetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAsset",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Asset.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.Asset> getGetAssetMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.Asset> getGetAssetMethod;
    if ((getGetAssetMethod = BrsApiServiceGrpc.getGetAssetMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAssetMethod = BrsApiServiceGrpc.getGetAssetMethod) == null) {
          BrsApiServiceGrpc.getGetAssetMethod = getGetAssetMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.Asset>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAsset"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Asset.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAsset"))
                  .build();
          }
        }
     }
     return getGetAssetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest,
      burst.kit.service.impl.grpc.BrsApi.AssetBalances> getGetAssetBalancesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetBalances",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.AssetBalances.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest,
      burst.kit.service.impl.grpc.BrsApi.AssetBalances> getGetAssetBalancesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest, burst.kit.service.impl.grpc.BrsApi.AssetBalances> getGetAssetBalancesMethod;
    if ((getGetAssetBalancesMethod = BrsApiServiceGrpc.getGetAssetBalancesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAssetBalancesMethod = BrsApiServiceGrpc.getGetAssetBalancesMethod) == null) {
          BrsApiServiceGrpc.getGetAssetBalancesMethod = getGetAssetBalancesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest, burst.kit.service.impl.grpc.BrsApi.AssetBalances>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAssetBalances"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.AssetBalances.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAssetBalances"))
                  .build();
          }
        }
     }
     return getGetAssetBalancesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest,
      burst.kit.service.impl.grpc.BrsApi.Assets> getGetAssetsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssets",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Assets.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest,
      burst.kit.service.impl.grpc.BrsApi.Assets> getGetAssetsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest, burst.kit.service.impl.grpc.BrsApi.Assets> getGetAssetsMethod;
    if ((getGetAssetsMethod = BrsApiServiceGrpc.getGetAssetsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAssetsMethod = BrsApiServiceGrpc.getGetAssetsMethod) == null) {
          BrsApiServiceGrpc.getGetAssetsMethod = getGetAssetsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest, burst.kit.service.impl.grpc.BrsApi.Assets>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAssets"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Assets.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAssets"))
                  .build();
          }
        }
     }
     return getGetAssetsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Assets> getGetAssetsByIssuerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetsByIssuer",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Assets.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Assets> getGetAssetsByIssuerMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Assets> getGetAssetsByIssuerMethod;
    if ((getGetAssetsByIssuerMethod = BrsApiServiceGrpc.getGetAssetsByIssuerMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAssetsByIssuerMethod = BrsApiServiceGrpc.getGetAssetsByIssuerMethod) == null) {
          BrsApiServiceGrpc.getGetAssetsByIssuerMethod = getGetAssetsByIssuerMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Assets>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAssetsByIssuer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Assets.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAssetsByIssuer"))
                  .build();
          }
        }
     }
     return getGetAssetsByIssuerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest,
      burst.kit.service.impl.grpc.BrsApi.AssetTrades> getGetAssetTradesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetTrades",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.AssetTrades.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest,
      burst.kit.service.impl.grpc.BrsApi.AssetTrades> getGetAssetTradesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest, burst.kit.service.impl.grpc.BrsApi.AssetTrades> getGetAssetTradesMethod;
    if ((getGetAssetTradesMethod = BrsApiServiceGrpc.getGetAssetTradesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAssetTradesMethod = BrsApiServiceGrpc.getGetAssetTradesMethod) == null) {
          BrsApiServiceGrpc.getGetAssetTradesMethod = getGetAssetTradesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest, burst.kit.service.impl.grpc.BrsApi.AssetTrades>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAssetTrades"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.AssetTrades.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAssetTrades"))
                  .build();
          }
        }
     }
     return getGetAssetTradesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest,
      burst.kit.service.impl.grpc.BrsApi.AssetTransfers> getGetAssetTransfersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAssetTransfers",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.AssetTransfers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest,
      burst.kit.service.impl.grpc.BrsApi.AssetTransfers> getGetAssetTransfersMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest, burst.kit.service.impl.grpc.BrsApi.AssetTransfers> getGetAssetTransfersMethod;
    if ((getGetAssetTransfersMethod = BrsApiServiceGrpc.getGetAssetTransfersMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetAssetTransfersMethod = BrsApiServiceGrpc.getGetAssetTransfersMethod) == null) {
          BrsApiServiceGrpc.getGetAssetTransfersMethod = getGetAssetTransfersMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest, burst.kit.service.impl.grpc.BrsApi.AssetTransfers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAssetTransfers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.AssetTransfers.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAssetTransfers"))
                  .build();
          }
        }
     }
     return getGetAssetTransfersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.AT> getGetATMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAT",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.AT.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.AT> getGetATMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.AT> getGetATMethod;
    if ((getGetATMethod = BrsApiServiceGrpc.getGetATMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetATMethod = BrsApiServiceGrpc.getGetATMethod) == null) {
          BrsApiServiceGrpc.getGetATMethod = getGetATMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.AT>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetAT"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.AT.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetAT"))
                  .build();
          }
        }
     }
     return getGetATMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.ATIds> getGetATIdsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetATIds",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.ATIds.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.ATIds> getGetATIdsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.ATIds> getGetATIdsMethod;
    if ((getGetATIdsMethod = BrsApiServiceGrpc.getGetATIdsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetATIdsMethod = BrsApiServiceGrpc.getGetATIdsMethod) == null) {
          BrsApiServiceGrpc.getGetATIdsMethod = getGetATIdsMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.ATIds>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetATIds"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.ATIds.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetATIds"))
                  .build();
          }
        }
     }
     return getGetATIdsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetBlockRequest,
      burst.kit.service.impl.grpc.BrsApi.Block> getGetBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlock",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetBlockRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Block.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetBlockRequest,
      burst.kit.service.impl.grpc.BrsApi.Block> getGetBlockMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetBlockRequest, burst.kit.service.impl.grpc.BrsApi.Block> getGetBlockMethod;
    if ((getGetBlockMethod = BrsApiServiceGrpc.getGetBlockMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetBlockMethod = BrsApiServiceGrpc.getGetBlockMethod) == null) {
          BrsApiServiceGrpc.getGetBlockMethod = getGetBlockMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetBlockRequest, burst.kit.service.impl.grpc.BrsApi.Block>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetBlockRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Block.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetBlock"))
                  .build();
          }
        }
     }
     return getGetBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest,
      burst.kit.service.impl.grpc.BrsApi.Blocks> getGetBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBlocks",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Blocks.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest,
      burst.kit.service.impl.grpc.BrsApi.Blocks> getGetBlocksMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest, burst.kit.service.impl.grpc.BrsApi.Blocks> getGetBlocksMethod;
    if ((getGetBlocksMethod = BrsApiServiceGrpc.getGetBlocksMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetBlocksMethod = BrsApiServiceGrpc.getGetBlocksMethod) == null) {
          BrsApiServiceGrpc.getGetBlocksMethod = getGetBlocksMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest, burst.kit.service.impl.grpc.BrsApi.Blocks>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Blocks.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetBlocks"))
                  .build();
          }
        }
     }
     return getGetBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.Constants> getGetConstantsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetConstants",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Constants.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.Constants> getGetConstantsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.Constants> getGetConstantsMethod;
    if ((getGetConstantsMethod = BrsApiServiceGrpc.getGetConstantsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetConstantsMethod = BrsApiServiceGrpc.getGetConstantsMethod) == null) {
          BrsApiServiceGrpc.getGetConstantsMethod = getGetConstantsMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.Constants>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetConstants"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Constants.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetConstants"))
                  .build();
          }
        }
     }
     return getGetConstantsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.Counts> getGetCountsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCounts",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Counts.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.Counts> getGetCountsMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.Counts> getGetCountsMethod;
    if ((getGetCountsMethod = BrsApiServiceGrpc.getGetCountsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetCountsMethod = BrsApiServiceGrpc.getGetCountsMethod) == null) {
          BrsApiServiceGrpc.getGetCountsMethod = getGetCountsMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.Counts>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetCounts"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Counts.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetCounts"))
                  .build();
          }
        }
     }
     return getGetCountsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.Time> getGetCurrentTimeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetCurrentTime",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Time.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.Time> getGetCurrentTimeMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.Time> getGetCurrentTimeMethod;
    if ((getGetCurrentTimeMethod = BrsApiServiceGrpc.getGetCurrentTimeMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetCurrentTimeMethod = BrsApiServiceGrpc.getGetCurrentTimeMethod) == null) {
          BrsApiServiceGrpc.getGetCurrentTimeMethod = getGetCurrentTimeMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.Time>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetCurrentTime"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Time.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetCurrentTime"))
                  .build();
          }
        }
     }
     return getGetCurrentTimeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsGood> getGetDgsGoodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDgsGood",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.DgsGood.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsGood> getGetDgsGoodMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.DgsGood> getGetDgsGoodMethod;
    if ((getGetDgsGoodMethod = BrsApiServiceGrpc.getGetDgsGoodMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetDgsGoodMethod = BrsApiServiceGrpc.getGetDgsGoodMethod) == null) {
          BrsApiServiceGrpc.getGetDgsGoodMethod = getGetDgsGoodMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.DgsGood>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetDgsGood"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.DgsGood.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetDgsGood"))
                  .build();
          }
        }
     }
     return getGetDgsGoodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsGoods> getGetDgsGoodsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDgsGoods",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.DgsGoods.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsGoods> getGetDgsGoodsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest, burst.kit.service.impl.grpc.BrsApi.DgsGoods> getGetDgsGoodsMethod;
    if ((getGetDgsGoodsMethod = BrsApiServiceGrpc.getGetDgsGoodsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetDgsGoodsMethod = BrsApiServiceGrpc.getGetDgsGoodsMethod) == null) {
          BrsApiServiceGrpc.getGetDgsGoodsMethod = getGetDgsGoodsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest, burst.kit.service.impl.grpc.BrsApi.DgsGoods>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetDgsGoods"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.DgsGoods.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetDgsGoods"))
                  .build();
          }
        }
     }
     return getGetDgsGoodsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getGetDgsPendingPurchasesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDgsPendingPurchases",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.DgsPurchases.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getGetDgsPendingPurchasesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest, burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getGetDgsPendingPurchasesMethod;
    if ((getGetDgsPendingPurchasesMethod = BrsApiServiceGrpc.getGetDgsPendingPurchasesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetDgsPendingPurchasesMethod = BrsApiServiceGrpc.getGetDgsPendingPurchasesMethod) == null) {
          BrsApiServiceGrpc.getGetDgsPendingPurchasesMethod = getGetDgsPendingPurchasesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest, burst.kit.service.impl.grpc.BrsApi.DgsPurchases>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetDgsPendingPurchases"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.DgsPurchases.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetDgsPendingPurchases"))
                  .build();
          }
        }
     }
     return getGetDgsPendingPurchasesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsPurchase> getGetDgsPurchaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDgsPurchase",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.DgsPurchase.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsPurchase> getGetDgsPurchaseMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.DgsPurchase> getGetDgsPurchaseMethod;
    if ((getGetDgsPurchaseMethod = BrsApiServiceGrpc.getGetDgsPurchaseMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetDgsPurchaseMethod = BrsApiServiceGrpc.getGetDgsPurchaseMethod) == null) {
          BrsApiServiceGrpc.getGetDgsPurchaseMethod = getGetDgsPurchaseMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.DgsPurchase>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetDgsPurchase"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.DgsPurchase.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetDgsPurchase"))
                  .build();
          }
        }
     }
     return getGetDgsPurchaseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getGetDgsPurchasesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetDgsPurchases",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.DgsPurchases.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest,
      burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getGetDgsPurchasesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest, burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getGetDgsPurchasesMethod;
    if ((getGetDgsPurchasesMethod = BrsApiServiceGrpc.getGetDgsPurchasesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetDgsPurchasesMethod = BrsApiServiceGrpc.getGetDgsPurchasesMethod) == null) {
          BrsApiServiceGrpc.getGetDgsPurchasesMethod = getGetDgsPurchasesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest, burst.kit.service.impl.grpc.BrsApi.DgsPurchases>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetDgsPurchases"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.DgsPurchases.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetDgsPurchases"))
                  .build();
          }
        }
     }
     return getGetDgsPurchasesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.EscrowTransaction> getGetEscrowTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetEscrowTransaction",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.EscrowTransaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.EscrowTransaction> getGetEscrowTransactionMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.EscrowTransaction> getGetEscrowTransactionMethod;
    if ((getGetEscrowTransactionMethod = BrsApiServiceGrpc.getGetEscrowTransactionMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetEscrowTransactionMethod = BrsApiServiceGrpc.getGetEscrowTransactionMethod) == null) {
          BrsApiServiceGrpc.getGetEscrowTransactionMethod = getGetEscrowTransactionMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.EscrowTransaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetEscrowTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.EscrowTransaction.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetEscrowTransaction"))
                  .build();
          }
        }
     }
     return getGetEscrowTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.MiningInfo> getGetMiningInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMiningInfo",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.MiningInfo.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.MiningInfo> getGetMiningInfoMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.MiningInfo> getGetMiningInfoMethod;
    if ((getGetMiningInfoMethod = BrsApiServiceGrpc.getGetMiningInfoMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetMiningInfoMethod = BrsApiServiceGrpc.getGetMiningInfoMethod) == null) {
          BrsApiServiceGrpc.getGetMiningInfoMethod = getGetMiningInfoMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.MiningInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetMiningInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.MiningInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetMiningInfo"))
                  .build();
          }
        }
     }
     return getGetMiningInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetOrderRequest,
      burst.kit.service.impl.grpc.BrsApi.Order> getGetOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOrder",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetOrderRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Order.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetOrderRequest,
      burst.kit.service.impl.grpc.BrsApi.Order> getGetOrderMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetOrderRequest, burst.kit.service.impl.grpc.BrsApi.Order> getGetOrderMethod;
    if ((getGetOrderMethod = BrsApiServiceGrpc.getGetOrderMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetOrderMethod = BrsApiServiceGrpc.getGetOrderMethod) == null) {
          BrsApiServiceGrpc.getGetOrderMethod = getGetOrderMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetOrderRequest, burst.kit.service.impl.grpc.BrsApi.Order>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetOrderRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Order.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetOrder"))
                  .build();
          }
        }
     }
     return getGetOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest,
      burst.kit.service.impl.grpc.BrsApi.Orders> getGetOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetOrders",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Orders.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest,
      burst.kit.service.impl.grpc.BrsApi.Orders> getGetOrdersMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest, burst.kit.service.impl.grpc.BrsApi.Orders> getGetOrdersMethod;
    if ((getGetOrdersMethod = BrsApiServiceGrpc.getGetOrdersMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetOrdersMethod = BrsApiServiceGrpc.getGetOrdersMethod) == null) {
          BrsApiServiceGrpc.getGetOrdersMethod = getGetOrdersMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest, burst.kit.service.impl.grpc.BrsApi.Orders>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Orders.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetOrders"))
                  .build();
          }
        }
     }
     return getGetOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetPeerRequest,
      burst.kit.service.impl.grpc.BrsApi.Peer> getGetPeerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPeer",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetPeerRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Peer.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetPeerRequest,
      burst.kit.service.impl.grpc.BrsApi.Peer> getGetPeerMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetPeerRequest, burst.kit.service.impl.grpc.BrsApi.Peer> getGetPeerMethod;
    if ((getGetPeerMethod = BrsApiServiceGrpc.getGetPeerMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetPeerMethod = BrsApiServiceGrpc.getGetPeerMethod) == null) {
          BrsApiServiceGrpc.getGetPeerMethod = getGetPeerMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetPeerRequest, burst.kit.service.impl.grpc.BrsApi.Peer>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetPeer"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetPeerRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Peer.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetPeer"))
                  .build();
          }
        }
     }
     return getGetPeerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetPeersRequest,
      burst.kit.service.impl.grpc.BrsApi.Peers> getGetPeersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPeers",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetPeersRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Peers.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetPeersRequest,
      burst.kit.service.impl.grpc.BrsApi.Peers> getGetPeersMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetPeersRequest, burst.kit.service.impl.grpc.BrsApi.Peers> getGetPeersMethod;
    if ((getGetPeersMethod = BrsApiServiceGrpc.getGetPeersMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetPeersMethod = BrsApiServiceGrpc.getGetPeersMethod) == null) {
          BrsApiServiceGrpc.getGetPeersMethod = getGetPeersMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetPeersRequest, burst.kit.service.impl.grpc.BrsApi.Peers>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetPeers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetPeersRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Peers.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetPeers"))
                  .build();
          }
        }
     }
     return getGetPeersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.State> getGetStateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetState",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.State.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.State> getGetStateMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.State> getGetStateMethod;
    if ((getGetStateMethod = BrsApiServiceGrpc.getGetStateMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetStateMethod = BrsApiServiceGrpc.getGetStateMethod) == null) {
          BrsApiServiceGrpc.getGetStateMethod = getGetStateMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.State>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetState"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.State.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetState"))
                  .build();
          }
        }
     }
     return getGetStateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.Subscription> getGetSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSubscription",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Subscription.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
      burst.kit.service.impl.grpc.BrsApi.Subscription> getGetSubscriptionMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.Subscription> getGetSubscriptionMethod;
    if ((getGetSubscriptionMethod = BrsApiServiceGrpc.getGetSubscriptionMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetSubscriptionMethod = BrsApiServiceGrpc.getGetSubscriptionMethod) == null) {
          BrsApiServiceGrpc.getGetSubscriptionMethod = getGetSubscriptionMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetByIdRequest, burst.kit.service.impl.grpc.BrsApi.Subscription>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetSubscription"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetByIdRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Subscription.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetSubscription"))
                  .build();
          }
        }
     }
     return getGetSubscriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Subscriptions> getGetSubscriptionsToAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetSubscriptionsToAccount",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Subscriptions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.Subscriptions> getGetSubscriptionsToAccountMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Subscriptions> getGetSubscriptionsToAccountMethod;
    if ((getGetSubscriptionsToAccountMethod = BrsApiServiceGrpc.getGetSubscriptionsToAccountMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetSubscriptionsToAccountMethod = BrsApiServiceGrpc.getGetSubscriptionsToAccountMethod) == null) {
          BrsApiServiceGrpc.getGetSubscriptionsToAccountMethod = getGetSubscriptionsToAccountMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.Subscriptions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetSubscriptionsToAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Subscriptions.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetSubscriptionsToAccount"))
                  .build();
          }
        }
     }
     return getGetSubscriptionsToAccountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest,
      burst.kit.service.impl.grpc.BrsApi.Transaction> getGetTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransaction",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.Transaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest,
      burst.kit.service.impl.grpc.BrsApi.Transaction> getGetTransactionMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest, burst.kit.service.impl.grpc.BrsApi.Transaction> getGetTransactionMethod;
    if ((getGetTransactionMethod = BrsApiServiceGrpc.getGetTransactionMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetTransactionMethod = BrsApiServiceGrpc.getGetTransactionMethod) == null) {
          BrsApiServiceGrpc.getGetTransactionMethod = getGetTransactionMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest, burst.kit.service.impl.grpc.BrsApi.Transaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.Transaction.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetTransaction"))
                  .build();
          }
        }
     }
     return getGetTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
      burst.kit.service.impl.grpc.BrsApi.TransactionBytes> getGetTransactionBytesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTransactionBytes",
      requestType = burst.kit.service.impl.grpc.BrsApi.BasicTransaction.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.TransactionBytes.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
      burst.kit.service.impl.grpc.BrsApi.TransactionBytes> getGetTransactionBytesMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.BasicTransaction, burst.kit.service.impl.grpc.BrsApi.TransactionBytes> getGetTransactionBytesMethod;
    if ((getGetTransactionBytesMethod = BrsApiServiceGrpc.getGetTransactionBytesMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetTransactionBytesMethod = BrsApiServiceGrpc.getGetTransactionBytesMethod) == null) {
          BrsApiServiceGrpc.getGetTransactionBytesMethod = getGetTransactionBytesMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.BasicTransaction, burst.kit.service.impl.grpc.BrsApi.TransactionBytes>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetTransactionBytes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.BasicTransaction.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.TransactionBytes.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetTransactionBytes"))
                  .build();
          }
        }
     }
     return getGetTransactionBytesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions> getGetUnconfirmedTransactionsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUnconfirmedTransactions",
      requestType = burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
      burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions> getGetUnconfirmedTransactionsMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions> getGetUnconfirmedTransactionsMethod;
    if ((getGetUnconfirmedTransactionsMethod = BrsApiServiceGrpc.getGetUnconfirmedTransactionsMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getGetUnconfirmedTransactionsMethod = BrsApiServiceGrpc.getGetUnconfirmedTransactionsMethod) == null) {
          BrsApiServiceGrpc.getGetUnconfirmedTransactionsMethod = getGetUnconfirmedTransactionsMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.GetAccountRequest, burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "GetUnconfirmedTransactions"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.GetAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("GetUnconfirmedTransactions"))
                  .build();
          }
        }
     }
     return getGetUnconfirmedTransactionsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.TransactionBytes,
      burst.kit.service.impl.grpc.BrsApi.BasicTransaction> getParseTransactionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ParseTransaction",
      requestType = burst.kit.service.impl.grpc.BrsApi.TransactionBytes.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.BasicTransaction.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.TransactionBytes,
      burst.kit.service.impl.grpc.BrsApi.BasicTransaction> getParseTransactionMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.TransactionBytes, burst.kit.service.impl.grpc.BrsApi.BasicTransaction> getParseTransactionMethod;
    if ((getParseTransactionMethod = BrsApiServiceGrpc.getParseTransactionMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getParseTransactionMethod = BrsApiServiceGrpc.getParseTransactionMethod) == null) {
          BrsApiServiceGrpc.getParseTransactionMethod = getParseTransactionMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.TransactionBytes, burst.kit.service.impl.grpc.BrsApi.BasicTransaction>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "ParseTransaction"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.TransactionBytes.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.BasicTransaction.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("ParseTransaction"))
                  .build();
          }
        }
     }
     return getParseTransactionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest,
      burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse> getSubmitNonceMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SubmitNonce",
      requestType = burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest,
      burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse> getSubmitNonceMethod() {
    io.grpc.MethodDescriptor<burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest, burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse> getSubmitNonceMethod;
    if ((getSubmitNonceMethod = BrsApiServiceGrpc.getSubmitNonceMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getSubmitNonceMethod = BrsApiServiceGrpc.getSubmitNonceMethod) == null) {
          BrsApiServiceGrpc.getSubmitNonceMethod = getSubmitNonceMethod = 
              io.grpc.MethodDescriptor.<burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest, burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "SubmitNonce"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("SubmitNonce"))
                  .build();
          }
        }
     }
     return getSubmitNonceMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.FeeSuggestion> getSuggestFeeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SuggestFee",
      requestType = com.google.protobuf.Empty.class,
      responseType = burst.kit.service.impl.grpc.BrsApi.FeeSuggestion.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.google.protobuf.Empty,
      burst.kit.service.impl.grpc.BrsApi.FeeSuggestion> getSuggestFeeMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.FeeSuggestion> getSuggestFeeMethod;
    if ((getSuggestFeeMethod = BrsApiServiceGrpc.getSuggestFeeMethod) == null) {
      synchronized (BrsApiServiceGrpc.class) {
        if ((getSuggestFeeMethod = BrsApiServiceGrpc.getSuggestFeeMethod) == null) {
          BrsApiServiceGrpc.getSuggestFeeMethod = getSuggestFeeMethod = 
              io.grpc.MethodDescriptor.<com.google.protobuf.Empty, burst.kit.service.impl.grpc.BrsApi.FeeSuggestion>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "BrsApiService", "SuggestFee"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.Empty.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  burst.kit.service.impl.grpc.BrsApi.FeeSuggestion.getDefaultInstance()))
                  .setSchemaDescriptor(new BrsApiServiceMethodDescriptorSupplier("SuggestFee"))
                  .build();
          }
        }
     }
     return getSuggestFeeMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static BrsApiServiceStub newStub(io.grpc.Channel channel) {
    return new BrsApiServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static BrsApiServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new BrsApiServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static BrsApiServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new BrsApiServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class BrsApiServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Broadcast a transaction to the network.
     * </pre>
     */
    public void broadcastTransaction(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBroadcastTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network. Takes transaction bytes instead of a BasicTransaction
     * </pre>
     */
    public void broadcastTransactionBytes(burst.kit.service.impl.grpc.BrsApi.TransactionBytes request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBroadcastTransactionBytesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Automatically fills in the following fields: Version (based on current transaction version), type and subtype (based on specified attachment), timestamp (current time). Additionally sets attachment to ordinary payment if it was not set
     * </pre>
     */
    public void completeBasicTransaction(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.BasicTransaction> responseObserver) {
      asyncUnimplementedUnaryCall(getCompleteBasicTransactionMethod(), responseObserver);
    }

    /**
     */
    public void getAccount(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Account> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the ATs that an account is the creator of, by the creator's account ID
     * </pre>
     */
    public void getAccountATs(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AccountATs> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountATsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the blocks that an account has forged, by the forger's ID
     * </pre>
     */
    public void getAccountBlocks(burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Blocks> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountBlocksMethod(), responseObserver);
    }

    /**
     */
    public void getAccountCurrentOrders(burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Orders> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountCurrentOrdersMethod(), responseObserver);
    }

    /**
     */
    public void getAccountEscrowTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.EscrowTransactions> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountEscrowTransactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get multiple accounts based on the criteria described in GetAccountsRequest. The criteria is an OR selection - I.E. if you specified a reward recipient and a name it would include accounts that have that recipient and that name, including duplicates. Therefore it is recommended to only select one criteria
     * </pre>
     */
    public void getAccounts(burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Accounts> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountsMethod(), responseObserver);
    }

    /**
     */
    public void getAccountSubscriptions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscriptions> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountSubscriptionsMethod(), responseObserver);
    }

    /**
     */
    public void getAccountTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Transactions> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountTransactionsMethod(), responseObserver);
    }

    /**
     */
    public void getAlias(burst.kit.service.impl.grpc.BrsApi.GetAliasRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Alias> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAliasMethod(), responseObserver);
    }

    /**
     */
    public void getAliases(burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Aliases> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAliasesMethod(), responseObserver);
    }

    /**
     */
    public void getAsset(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Asset> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get an asset's holders and their balances
     * </pre>
     */
    public void getAssetBalances(burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetBalances> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetBalancesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get multiple assets in one go.
     * </pre>
     */
    public void getAssets(burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Assets> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all assets issued by an account
     * </pre>
     */
    public void getAssetsByIssuer(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Assets> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetsByIssuerMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all asset trades made by an account
     * </pre>
     */
    public void getAssetTrades(burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetTrades> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetTradesMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all asset transfers made by an account
     * </pre>
     */
    public void getAssetTransfers(burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetTransfers> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAssetTransfersMethod(), responseObserver);
    }

    /**
     */
    public void getAT(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AT> responseObserver) {
      asyncUnimplementedUnaryCall(getGetATMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get all active AT IDs
     * </pre>
     */
    public void getATIds(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.ATIds> responseObserver) {
      asyncUnimplementedUnaryCall(getGetATIdsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get a block by ID, height or timestamp
     * </pre>
     */
    public void getBlock(burst.kit.service.impl.grpc.BrsApi.GetBlockRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Block> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the most recent blocks
     * </pre>
     */
    public void getBlocks(burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Blocks> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlocksMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the current blockchain constants
     * </pre>
     */
    public void getConstants(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Constants> responseObserver) {
      asyncUnimplementedUnaryCall(getGetConstantsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the counts of different blockchain entities
     * </pre>
     */
    public void getCounts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Counts> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCountsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the current Burst time (number of seconds since Burst epoch)
     * </pre>
     */
    public void getCurrentTime(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Time> responseObserver) {
      asyncUnimplementedUnaryCall(getGetCurrentTimeMethod(), responseObserver);
    }

    /**
     */
    public void getDgsGood(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsGood> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDgsGoodMethod(), responseObserver);
    }

    /**
     */
    public void getDgsGoods(burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsGoods> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDgsGoodsMethod(), responseObserver);
    }

    /**
     */
    public void getDgsPendingPurchases(burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchases> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDgsPendingPurchasesMethod(), responseObserver);
    }

    /**
     */
    public void getDgsPurchase(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchase> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDgsPurchaseMethod(), responseObserver);
    }

    /**
     */
    public void getDgsPurchases(burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchases> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDgsPurchasesMethod(), responseObserver);
    }

    /**
     */
    public void getEscrowTransaction(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.EscrowTransaction> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEscrowTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the mining info for the next block. This is needed to mine.
     * </pre>
     */
    public void getMiningInfo(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.MiningInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMiningInfoMethod(), responseObserver);
    }

    /**
     */
    public void getOrder(burst.kit.service.impl.grpc.BrsApi.GetOrderRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Order> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOrderMethod(), responseObserver);
    }

    /**
     */
    public void getOrders(burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Orders> responseObserver) {
      asyncUnimplementedUnaryCall(getGetOrdersMethod(), responseObserver);
    }

    /**
     */
    public void getPeer(burst.kit.service.impl.grpc.BrsApi.GetPeerRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Peer> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPeerMethod(), responseObserver);
    }

    /**
     */
    public void getPeers(burst.kit.service.impl.grpc.BrsApi.GetPeersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Peers> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPeersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get the current server state
     * </pre>
     */
    public void getState(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.State> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStateMethod(), responseObserver);
    }

    /**
     */
    public void getSubscription(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscription> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSubscriptionMethod(), responseObserver);
    }

    /**
     */
    public void getSubscriptionsToAccount(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscriptions> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSubscriptionsToAccountMethod(), responseObserver);
    }

    /**
     */
    public void getTransaction(burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Transaction> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Convert a BasicTranscation into its transaction bytes, to be signed. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public void getTransactionBytes(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBytes> responseObserver) {
      asyncUnimplementedUnaryCall(getGetTransactionBytesMethod(), responseObserver);
    }

    /**
     */
    public void getUnconfirmedTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUnconfirmedTransactionsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Convert TransactionBytes into a BasicTransaction. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public void parseTransaction(burst.kit.service.impl.grpc.BrsApi.TransactionBytes request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.BasicTransaction> responseObserver) {
      asyncUnimplementedUnaryCall(getParseTransactionMethod(), responseObserver);
    }

    /**
     * <pre>
     * Submit a nonce to try to forge a block. This requires the passphrase to be sent to the server so should only be performed on local nodes.
     * </pre>
     */
    public void submitNonce(burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getSubmitNonceMethod(), responseObserver);
    }

    /**
     * <pre>
     * Suggest a fee to use for a transaction
     * </pre>
     */
    public void suggestFee(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.FeeSuggestion> responseObserver) {
      asyncUnimplementedUnaryCall(getSuggestFeeMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getBroadcastTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
                burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult>(
                  this, METHODID_BROADCAST_TRANSACTION)))
          .addMethod(
            getBroadcastTransactionBytesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.TransactionBytes,
                burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult>(
                  this, METHODID_BROADCAST_TRANSACTION_BYTES)))
          .addMethod(
            getCompleteBasicTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
                burst.kit.service.impl.grpc.BrsApi.BasicTransaction>(
                  this, METHODID_COMPLETE_BASIC_TRANSACTION)))
          .addMethod(
            getGetAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.Account>(
                  this, METHODID_GET_ACCOUNT)))
          .addMethod(
            getGetAccountATsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.AccountATs>(
                  this, METHODID_GET_ACCOUNT_ATS)))
          .addMethod(
            getGetAccountBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest,
                burst.kit.service.impl.grpc.BrsApi.Blocks>(
                  this, METHODID_GET_ACCOUNT_BLOCKS)))
          .addMethod(
            getGetAccountCurrentOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest,
                burst.kit.service.impl.grpc.BrsApi.Orders>(
                  this, METHODID_GET_ACCOUNT_CURRENT_ORDERS)))
          .addMethod(
            getGetAccountEscrowTransactionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.EscrowTransactions>(
                  this, METHODID_GET_ACCOUNT_ESCROW_TRANSACTIONS)))
          .addMethod(
            getGetAccountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest,
                burst.kit.service.impl.grpc.BrsApi.Accounts>(
                  this, METHODID_GET_ACCOUNTS)))
          .addMethod(
            getGetAccountSubscriptionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.Subscriptions>(
                  this, METHODID_GET_ACCOUNT_SUBSCRIPTIONS)))
          .addMethod(
            getGetAccountTransactionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest,
                burst.kit.service.impl.grpc.BrsApi.Transactions>(
                  this, METHODID_GET_ACCOUNT_TRANSACTIONS)))
          .addMethod(
            getGetAliasMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAliasRequest,
                burst.kit.service.impl.grpc.BrsApi.Alias>(
                  this, METHODID_GET_ALIAS)))
          .addMethod(
            getGetAliasesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest,
                burst.kit.service.impl.grpc.BrsApi.Aliases>(
                  this, METHODID_GET_ALIASES)))
          .addMethod(
            getGetAssetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
                burst.kit.service.impl.grpc.BrsApi.Asset>(
                  this, METHODID_GET_ASSET)))
          .addMethod(
            getGetAssetBalancesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest,
                burst.kit.service.impl.grpc.BrsApi.AssetBalances>(
                  this, METHODID_GET_ASSET_BALANCES)))
          .addMethod(
            getGetAssetsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest,
                burst.kit.service.impl.grpc.BrsApi.Assets>(
                  this, METHODID_GET_ASSETS)))
          .addMethod(
            getGetAssetsByIssuerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.Assets>(
                  this, METHODID_GET_ASSETS_BY_ISSUER)))
          .addMethod(
            getGetAssetTradesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest,
                burst.kit.service.impl.grpc.BrsApi.AssetTrades>(
                  this, METHODID_GET_ASSET_TRADES)))
          .addMethod(
            getGetAssetTransfersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest,
                burst.kit.service.impl.grpc.BrsApi.AssetTransfers>(
                  this, METHODID_GET_ASSET_TRANSFERS)))
          .addMethod(
            getGetATMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
                burst.kit.service.impl.grpc.BrsApi.AT>(
                  this, METHODID_GET_AT)))
          .addMethod(
            getGetATIdsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.ATIds>(
                  this, METHODID_GET_ATIDS)))
          .addMethod(
            getGetBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetBlockRequest,
                burst.kit.service.impl.grpc.BrsApi.Block>(
                  this, METHODID_GET_BLOCK)))
          .addMethod(
            getGetBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest,
                burst.kit.service.impl.grpc.BrsApi.Blocks>(
                  this, METHODID_GET_BLOCKS)))
          .addMethod(
            getGetConstantsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.Constants>(
                  this, METHODID_GET_CONSTANTS)))
          .addMethod(
            getGetCountsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.Counts>(
                  this, METHODID_GET_COUNTS)))
          .addMethod(
            getGetCurrentTimeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.Time>(
                  this, METHODID_GET_CURRENT_TIME)))
          .addMethod(
            getGetDgsGoodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
                burst.kit.service.impl.grpc.BrsApi.DgsGood>(
                  this, METHODID_GET_DGS_GOOD)))
          .addMethod(
            getGetDgsGoodsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest,
                burst.kit.service.impl.grpc.BrsApi.DgsGoods>(
                  this, METHODID_GET_DGS_GOODS)))
          .addMethod(
            getGetDgsPendingPurchasesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest,
                burst.kit.service.impl.grpc.BrsApi.DgsPurchases>(
                  this, METHODID_GET_DGS_PENDING_PURCHASES)))
          .addMethod(
            getGetDgsPurchaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
                burst.kit.service.impl.grpc.BrsApi.DgsPurchase>(
                  this, METHODID_GET_DGS_PURCHASE)))
          .addMethod(
            getGetDgsPurchasesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest,
                burst.kit.service.impl.grpc.BrsApi.DgsPurchases>(
                  this, METHODID_GET_DGS_PURCHASES)))
          .addMethod(
            getGetEscrowTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
                burst.kit.service.impl.grpc.BrsApi.EscrowTransaction>(
                  this, METHODID_GET_ESCROW_TRANSACTION)))
          .addMethod(
            getGetMiningInfoMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.MiningInfo>(
                  this, METHODID_GET_MINING_INFO)))
          .addMethod(
            getGetOrderMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetOrderRequest,
                burst.kit.service.impl.grpc.BrsApi.Order>(
                  this, METHODID_GET_ORDER)))
          .addMethod(
            getGetOrdersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest,
                burst.kit.service.impl.grpc.BrsApi.Orders>(
                  this, METHODID_GET_ORDERS)))
          .addMethod(
            getGetPeerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetPeerRequest,
                burst.kit.service.impl.grpc.BrsApi.Peer>(
                  this, METHODID_GET_PEER)))
          .addMethod(
            getGetPeersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetPeersRequest,
                burst.kit.service.impl.grpc.BrsApi.Peers>(
                  this, METHODID_GET_PEERS)))
          .addMethod(
            getGetStateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.State>(
                  this, METHODID_GET_STATE)))
          .addMethod(
            getGetSubscriptionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetByIdRequest,
                burst.kit.service.impl.grpc.BrsApi.Subscription>(
                  this, METHODID_GET_SUBSCRIPTION)))
          .addMethod(
            getGetSubscriptionsToAccountMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.Subscriptions>(
                  this, METHODID_GET_SUBSCRIPTIONS_TO_ACCOUNT)))
          .addMethod(
            getGetTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest,
                burst.kit.service.impl.grpc.BrsApi.Transaction>(
                  this, METHODID_GET_TRANSACTION)))
          .addMethod(
            getGetTransactionBytesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.BasicTransaction,
                burst.kit.service.impl.grpc.BrsApi.TransactionBytes>(
                  this, METHODID_GET_TRANSACTION_BYTES)))
          .addMethod(
            getGetUnconfirmedTransactionsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.GetAccountRequest,
                burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions>(
                  this, METHODID_GET_UNCONFIRMED_TRANSACTIONS)))
          .addMethod(
            getParseTransactionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.TransactionBytes,
                burst.kit.service.impl.grpc.BrsApi.BasicTransaction>(
                  this, METHODID_PARSE_TRANSACTION)))
          .addMethod(
            getSubmitNonceMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest,
                burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse>(
                  this, METHODID_SUBMIT_NONCE)))
          .addMethod(
            getSuggestFeeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.google.protobuf.Empty,
                burst.kit.service.impl.grpc.BrsApi.FeeSuggestion>(
                  this, METHODID_SUGGEST_FEE)))
          .build();
    }
  }

  /**
   */
  public static final class BrsApiServiceStub extends io.grpc.stub.AbstractStub<BrsApiServiceStub> {
    private BrsApiServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrsApiServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrsApiServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrsApiServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network.
     * </pre>
     */
    public void broadcastTransaction(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBroadcastTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network. Takes transaction bytes instead of a BasicTransaction
     * </pre>
     */
    public void broadcastTransactionBytes(burst.kit.service.impl.grpc.BrsApi.TransactionBytes request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBroadcastTransactionBytesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Automatically fills in the following fields: Version (based on current transaction version), type and subtype (based on specified attachment), timestamp (current time). Additionally sets attachment to ordinary payment if it was not set
     * </pre>
     */
    public void completeBasicTransaction(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.BasicTransaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCompleteBasicTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccount(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Account> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the ATs that an account is the creator of, by the creator's account ID
     * </pre>
     */
    public void getAccountATs(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AccountATs> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountATsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the blocks that an account has forged, by the forger's ID
     * </pre>
     */
    public void getAccountBlocks(burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Blocks> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountCurrentOrders(burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Orders> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountCurrentOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountEscrowTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.EscrowTransactions> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountEscrowTransactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get multiple accounts based on the criteria described in GetAccountsRequest. The criteria is an OR selection - I.E. if you specified a reward recipient and a name it would include accounts that have that recipient and that name, including duplicates. Therefore it is recommended to only select one criteria
     * </pre>
     */
    public void getAccounts(burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Accounts> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountSubscriptions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscriptions> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountSubscriptionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAccountTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Transactions> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountTransactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAlias(burst.kit.service.impl.grpc.BrsApi.GetAliasRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Alias> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAliasMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAliases(burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Aliases> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAliasesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAsset(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Asset> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get an asset's holders and their balances
     * </pre>
     */
    public void getAssetBalances(burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetBalances> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetBalancesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get multiple assets in one go.
     * </pre>
     */
    public void getAssets(burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Assets> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all assets issued by an account
     * </pre>
     */
    public void getAssetsByIssuer(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Assets> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetsByIssuerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all asset trades made by an account
     * </pre>
     */
    public void getAssetTrades(burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetTrades> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetTradesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all asset transfers made by an account
     * </pre>
     */
    public void getAssetTransfers(burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetTransfers> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAssetTransfersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAT(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AT> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetATMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get all active AT IDs
     * </pre>
     */
    public void getATIds(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.ATIds> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetATIdsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get a block by ID, height or timestamp
     * </pre>
     */
    public void getBlock(burst.kit.service.impl.grpc.BrsApi.GetBlockRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Block> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the most recent blocks
     * </pre>
     */
    public void getBlocks(burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Blocks> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the current blockchain constants
     * </pre>
     */
    public void getConstants(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Constants> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetConstantsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the counts of different blockchain entities
     * </pre>
     */
    public void getCounts(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Counts> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCountsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the current Burst time (number of seconds since Burst epoch)
     * </pre>
     */
    public void getCurrentTime(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Time> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetCurrentTimeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDgsGood(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsGood> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDgsGoodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDgsGoods(burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsGoods> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDgsGoodsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDgsPendingPurchases(burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchases> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDgsPendingPurchasesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDgsPurchase(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchase> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDgsPurchaseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDgsPurchases(burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchases> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDgsPurchasesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEscrowTransaction(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.EscrowTransaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEscrowTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the mining info for the next block. This is needed to mine.
     * </pre>
     */
    public void getMiningInfo(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.MiningInfo> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetMiningInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrder(burst.kit.service.impl.grpc.BrsApi.GetOrderRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Order> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getOrders(burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Orders> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPeer(burst.kit.service.impl.grpc.BrsApi.GetPeerRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Peer> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPeerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPeers(burst.kit.service.impl.grpc.BrsApi.GetPeersRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Peers> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPeersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get the current server state
     * </pre>
     */
    public void getState(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.State> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSubscription(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscription> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getSubscriptionsToAccount(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscriptions> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSubscriptionsToAccountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTransaction(burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Transaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Convert a BasicTranscation into its transaction bytes, to be signed. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public void getTransactionBytes(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBytes> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetTransactionBytesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getUnconfirmedTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUnconfirmedTransactionsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Convert TransactionBytes into a BasicTransaction. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public void parseTransaction(burst.kit.service.impl.grpc.BrsApi.TransactionBytes request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.BasicTransaction> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getParseTransactionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Submit a nonce to try to forge a block. This requires the passphrase to be sent to the server so should only be performed on local nodes.
     * </pre>
     */
    public void submitNonce(burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSubmitNonceMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Suggest a fee to use for a transaction
     * </pre>
     */
    public void suggestFee(com.google.protobuf.Empty request,
        io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.FeeSuggestion> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSuggestFeeMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class BrsApiServiceBlockingStub extends io.grpc.stub.AbstractStub<BrsApiServiceBlockingStub> {
    private BrsApiServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrsApiServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrsApiServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrsApiServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network.
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult broadcastTransaction(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request) {
      return blockingUnaryCall(
          getChannel(), getBroadcastTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network. Takes transaction bytes instead of a BasicTransaction
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult broadcastTransactionBytes(burst.kit.service.impl.grpc.BrsApi.TransactionBytes request) {
      return blockingUnaryCall(
          getChannel(), getBroadcastTransactionBytesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Automatically fills in the following fields: Version (based on current transaction version), type and subtype (based on specified attachment), timestamp (current time). Additionally sets attachment to ordinary payment if it was not set
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.BasicTransaction completeBasicTransaction(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request) {
      return blockingUnaryCall(
          getChannel(), getCompleteBasicTransactionMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Account getAccount(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the ATs that an account is the creator of, by the creator's account ID
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.AccountATs getAccountATs(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountATsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the blocks that an account has forged, by the forger's ID
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Blocks getAccountBlocks(burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountBlocksMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Orders getAccountCurrentOrders(burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountCurrentOrdersMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.EscrowTransactions getAccountEscrowTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountEscrowTransactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get multiple accounts based on the criteria described in GetAccountsRequest. The criteria is an OR selection - I.E. if you specified a reward recipient and a name it would include accounts that have that recipient and that name, including duplicates. Therefore it is recommended to only select one criteria
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Accounts getAccounts(burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountsMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Subscriptions getAccountSubscriptions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountSubscriptionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Transactions getAccountTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountTransactionsMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Alias getAlias(burst.kit.service.impl.grpc.BrsApi.GetAliasRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAliasMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Aliases getAliases(burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAliasesMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Asset getAsset(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get an asset's holders and their balances
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.AssetBalances getAssetBalances(burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetBalancesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get multiple assets in one go.
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Assets getAssets(burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all assets issued by an account
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Assets getAssetsByIssuer(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetsByIssuerMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all asset trades made by an account
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.AssetTrades getAssetTrades(burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetTradesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all asset transfers made by an account
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.AssetTransfers getAssetTransfers(burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetAssetTransfersMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.AT getAT(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetATMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get all active AT IDs
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.ATIds getATIds(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetATIdsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get a block by ID, height or timestamp
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Block getBlock(burst.kit.service.impl.grpc.BrsApi.GetBlockRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the most recent blocks
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Blocks getBlocks(burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetBlocksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the current blockchain constants
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Constants getConstants(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetConstantsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the counts of different blockchain entities
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Counts getCounts(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetCountsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the current Burst time (number of seconds since Burst epoch)
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.Time getCurrentTime(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetCurrentTimeMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.DgsGood getDgsGood(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDgsGoodMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.DgsGoods getDgsGoods(burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDgsGoodsMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.DgsPurchases getDgsPendingPurchases(burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDgsPendingPurchasesMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.DgsPurchase getDgsPurchase(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDgsPurchaseMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.DgsPurchases getDgsPurchases(burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetDgsPurchasesMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.EscrowTransaction getEscrowTransaction(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetEscrowTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the mining info for the next block. This is needed to mine.
     * </pre>
     */
    public java.util.Iterator<burst.kit.service.impl.grpc.BrsApi.MiningInfo> getMiningInfo(
        com.google.protobuf.Empty request) {
      return blockingServerStreamingCall(
          getChannel(), getGetMiningInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Order getOrder(burst.kit.service.impl.grpc.BrsApi.GetOrderRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOrderMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Orders getOrders(burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetOrdersMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Peer getPeer(burst.kit.service.impl.grpc.BrsApi.GetPeerRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetPeerMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Peers getPeers(burst.kit.service.impl.grpc.BrsApi.GetPeersRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetPeersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get the current server state
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.State getState(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getGetStateMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Subscription getSubscription(burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSubscriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Subscriptions getSubscriptionsToAccount(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetSubscriptionsToAccountMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.Transaction getTransaction(burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Convert a BasicTranscation into its transaction bytes, to be signed. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.TransactionBytes getTransactionBytes(burst.kit.service.impl.grpc.BrsApi.BasicTransaction request) {
      return blockingUnaryCall(
          getChannel(), getGetTransactionBytesMethod(), getCallOptions(), request);
    }

    /**
     */
    public burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions getUnconfirmedTransactions(burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUnconfirmedTransactionsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Convert TransactionBytes into a BasicTransaction. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.BasicTransaction parseTransaction(burst.kit.service.impl.grpc.BrsApi.TransactionBytes request) {
      return blockingUnaryCall(
          getChannel(), getParseTransactionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Submit a nonce to try to forge a block. This requires the passphrase to be sent to the server so should only be performed on local nodes.
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse submitNonce(burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest request) {
      return blockingUnaryCall(
          getChannel(), getSubmitNonceMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Suggest a fee to use for a transaction
     * </pre>
     */
    public burst.kit.service.impl.grpc.BrsApi.FeeSuggestion suggestFee(com.google.protobuf.Empty request) {
      return blockingUnaryCall(
          getChannel(), getSuggestFeeMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class BrsApiServiceFutureStub extends io.grpc.stub.AbstractStub<BrsApiServiceFutureStub> {
    private BrsApiServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private BrsApiServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected BrsApiServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new BrsApiServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> broadcastTransaction(
        burst.kit.service.impl.grpc.BrsApi.BasicTransaction request) {
      return futureUnaryCall(
          getChannel().newCall(getBroadcastTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Broadcast a transaction to the network. Takes transaction bytes instead of a BasicTransaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult> broadcastTransactionBytes(
        burst.kit.service.impl.grpc.BrsApi.TransactionBytes request) {
      return futureUnaryCall(
          getChannel().newCall(getBroadcastTransactionBytesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Automatically fills in the following fields: Version (based on current transaction version), type and subtype (based on specified attachment), timestamp (current time). Additionally sets attachment to ordinary payment if it was not set
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.BasicTransaction> completeBasicTransaction(
        burst.kit.service.impl.grpc.BrsApi.BasicTransaction request) {
      return futureUnaryCall(
          getChannel().newCall(getCompleteBasicTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Account> getAccount(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the ATs that an account is the creator of, by the creator's account ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.AccountATs> getAccountATs(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountATsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the blocks that an account has forged, by the forger's ID
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Blocks> getAccountBlocks(
        burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountBlocksMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Orders> getAccountCurrentOrders(
        burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountCurrentOrdersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.EscrowTransactions> getAccountEscrowTransactions(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountEscrowTransactionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get multiple accounts based on the criteria described in GetAccountsRequest. The criteria is an OR selection - I.E. if you specified a reward recipient and a name it would include accounts that have that recipient and that name, including duplicates. Therefore it is recommended to only select one criteria
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Accounts> getAccounts(
        burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Subscriptions> getAccountSubscriptions(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountSubscriptionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Transactions> getAccountTransactions(
        burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountTransactionsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Alias> getAlias(
        burst.kit.service.impl.grpc.BrsApi.GetAliasRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAliasMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Aliases> getAliases(
        burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAliasesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Asset> getAsset(
        burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get an asset's holders and their balances
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.AssetBalances> getAssetBalances(
        burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetBalancesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get multiple assets in one go.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Assets> getAssets(
        burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all assets issued by an account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Assets> getAssetsByIssuer(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetsByIssuerMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all asset trades made by an account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.AssetTrades> getAssetTrades(
        burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetTradesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all asset transfers made by an account
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.AssetTransfers> getAssetTransfers(
        burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAssetTransfersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.AT> getAT(
        burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetATMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get all active AT IDs
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.ATIds> getATIds(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetATIdsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get a block by ID, height or timestamp
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Block> getBlock(
        burst.kit.service.impl.grpc.BrsApi.GetBlockRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the most recent blocks
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Blocks> getBlocks(
        burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the current blockchain constants
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Constants> getConstants(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetConstantsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the counts of different blockchain entities
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Counts> getCounts(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCountsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the current Burst time (number of seconds since Burst epoch)
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Time> getCurrentTime(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetCurrentTimeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.DgsGood> getDgsGood(
        burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDgsGoodMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.DgsGoods> getDgsGoods(
        burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDgsGoodsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getDgsPendingPurchases(
        burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDgsPendingPurchasesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.DgsPurchase> getDgsPurchase(
        burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDgsPurchaseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.DgsPurchases> getDgsPurchases(
        burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDgsPurchasesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.EscrowTransaction> getEscrowTransaction(
        burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEscrowTransactionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Order> getOrder(
        burst.kit.service.impl.grpc.BrsApi.GetOrderRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOrderMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Orders> getOrders(
        burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetOrdersMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Peer> getPeer(
        burst.kit.service.impl.grpc.BrsApi.GetPeerRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPeerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Peers> getPeers(
        burst.kit.service.impl.grpc.BrsApi.GetPeersRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPeersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get the current server state
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.State> getState(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Subscription> getSubscription(
        burst.kit.service.impl.grpc.BrsApi.GetByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSubscriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Subscriptions> getSubscriptionsToAccount(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSubscriptionsToAccountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.Transaction> getTransaction(
        burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Convert a BasicTranscation into its transaction bytes, to be signed. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.TransactionBytes> getTransactionBytes(
        burst.kit.service.impl.grpc.BrsApi.BasicTransaction request) {
      return futureUnaryCall(
          getChannel().newCall(getGetTransactionBytesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions> getUnconfirmedTransactions(
        burst.kit.service.impl.grpc.BrsApi.GetAccountRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUnconfirmedTransactionsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Convert TransactionBytes into a BasicTransaction. This theoretically can be done offline so will be removed in the future.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.BasicTransaction> parseTransaction(
        burst.kit.service.impl.grpc.BrsApi.TransactionBytes request) {
      return futureUnaryCall(
          getChannel().newCall(getParseTransactionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Submit a nonce to try to forge a block. This requires the passphrase to be sent to the server so should only be performed on local nodes.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse> submitNonce(
        burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getSubmitNonceMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Suggest a fee to use for a transaction
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<burst.kit.service.impl.grpc.BrsApi.FeeSuggestion> suggestFee(
        com.google.protobuf.Empty request) {
      return futureUnaryCall(
          getChannel().newCall(getSuggestFeeMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_BROADCAST_TRANSACTION = 0;
  private static final int METHODID_BROADCAST_TRANSACTION_BYTES = 1;
  private static final int METHODID_COMPLETE_BASIC_TRANSACTION = 2;
  private static final int METHODID_GET_ACCOUNT = 3;
  private static final int METHODID_GET_ACCOUNT_ATS = 4;
  private static final int METHODID_GET_ACCOUNT_BLOCKS = 5;
  private static final int METHODID_GET_ACCOUNT_CURRENT_ORDERS = 6;
  private static final int METHODID_GET_ACCOUNT_ESCROW_TRANSACTIONS = 7;
  private static final int METHODID_GET_ACCOUNTS = 8;
  private static final int METHODID_GET_ACCOUNT_SUBSCRIPTIONS = 9;
  private static final int METHODID_GET_ACCOUNT_TRANSACTIONS = 10;
  private static final int METHODID_GET_ALIAS = 11;
  private static final int METHODID_GET_ALIASES = 12;
  private static final int METHODID_GET_ASSET = 13;
  private static final int METHODID_GET_ASSET_BALANCES = 14;
  private static final int METHODID_GET_ASSETS = 15;
  private static final int METHODID_GET_ASSETS_BY_ISSUER = 16;
  private static final int METHODID_GET_ASSET_TRADES = 17;
  private static final int METHODID_GET_ASSET_TRANSFERS = 18;
  private static final int METHODID_GET_AT = 19;
  private static final int METHODID_GET_ATIDS = 20;
  private static final int METHODID_GET_BLOCK = 21;
  private static final int METHODID_GET_BLOCKS = 22;
  private static final int METHODID_GET_CONSTANTS = 23;
  private static final int METHODID_GET_COUNTS = 24;
  private static final int METHODID_GET_CURRENT_TIME = 25;
  private static final int METHODID_GET_DGS_GOOD = 26;
  private static final int METHODID_GET_DGS_GOODS = 27;
  private static final int METHODID_GET_DGS_PENDING_PURCHASES = 28;
  private static final int METHODID_GET_DGS_PURCHASE = 29;
  private static final int METHODID_GET_DGS_PURCHASES = 30;
  private static final int METHODID_GET_ESCROW_TRANSACTION = 31;
  private static final int METHODID_GET_MINING_INFO = 32;
  private static final int METHODID_GET_ORDER = 33;
  private static final int METHODID_GET_ORDERS = 34;
  private static final int METHODID_GET_PEER = 35;
  private static final int METHODID_GET_PEERS = 36;
  private static final int METHODID_GET_STATE = 37;
  private static final int METHODID_GET_SUBSCRIPTION = 38;
  private static final int METHODID_GET_SUBSCRIPTIONS_TO_ACCOUNT = 39;
  private static final int METHODID_GET_TRANSACTION = 40;
  private static final int METHODID_GET_TRANSACTION_BYTES = 41;
  private static final int METHODID_GET_UNCONFIRMED_TRANSACTIONS = 42;
  private static final int METHODID_PARSE_TRANSACTION = 43;
  private static final int METHODID_SUBMIT_NONCE = 44;
  private static final int METHODID_SUGGEST_FEE = 45;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final BrsApiServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(BrsApiServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BROADCAST_TRANSACTION:
          serviceImpl.broadcastTransaction((burst.kit.service.impl.grpc.BrsApi.BasicTransaction) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult>) responseObserver);
          break;
        case METHODID_BROADCAST_TRANSACTION_BYTES:
          serviceImpl.broadcastTransactionBytes((burst.kit.service.impl.grpc.BrsApi.TransactionBytes) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBroadcastResult>) responseObserver);
          break;
        case METHODID_COMPLETE_BASIC_TRANSACTION:
          serviceImpl.completeBasicTransaction((burst.kit.service.impl.grpc.BrsApi.BasicTransaction) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.BasicTransaction>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT:
          serviceImpl.getAccount((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Account>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_ATS:
          serviceImpl.getAccountATs((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AccountATs>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_BLOCKS:
          serviceImpl.getAccountBlocks((burst.kit.service.impl.grpc.BrsApi.GetAccountBlocksRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Blocks>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_CURRENT_ORDERS:
          serviceImpl.getAccountCurrentOrders((burst.kit.service.impl.grpc.BrsApi.GetAccountOrdersRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Orders>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_ESCROW_TRANSACTIONS:
          serviceImpl.getAccountEscrowTransactions((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.EscrowTransactions>) responseObserver);
          break;
        case METHODID_GET_ACCOUNTS:
          serviceImpl.getAccounts((burst.kit.service.impl.grpc.BrsApi.GetAccountsRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Accounts>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_SUBSCRIPTIONS:
          serviceImpl.getAccountSubscriptions((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscriptions>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_TRANSACTIONS:
          serviceImpl.getAccountTransactions((burst.kit.service.impl.grpc.BrsApi.GetAccountTransactionsRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Transactions>) responseObserver);
          break;
        case METHODID_GET_ALIAS:
          serviceImpl.getAlias((burst.kit.service.impl.grpc.BrsApi.GetAliasRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Alias>) responseObserver);
          break;
        case METHODID_GET_ALIASES:
          serviceImpl.getAliases((burst.kit.service.impl.grpc.BrsApi.GetAliasesRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Aliases>) responseObserver);
          break;
        case METHODID_GET_ASSET:
          serviceImpl.getAsset((burst.kit.service.impl.grpc.BrsApi.GetByIdRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Asset>) responseObserver);
          break;
        case METHODID_GET_ASSET_BALANCES:
          serviceImpl.getAssetBalances((burst.kit.service.impl.grpc.BrsApi.GetAssetBalancesRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetBalances>) responseObserver);
          break;
        case METHODID_GET_ASSETS:
          serviceImpl.getAssets((burst.kit.service.impl.grpc.BrsApi.GetAssetsRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Assets>) responseObserver);
          break;
        case METHODID_GET_ASSETS_BY_ISSUER:
          serviceImpl.getAssetsByIssuer((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Assets>) responseObserver);
          break;
        case METHODID_GET_ASSET_TRADES:
          serviceImpl.getAssetTrades((burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetTrades>) responseObserver);
          break;
        case METHODID_GET_ASSET_TRANSFERS:
          serviceImpl.getAssetTransfers((burst.kit.service.impl.grpc.BrsApi.GetAssetTransfersRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AssetTransfers>) responseObserver);
          break;
        case METHODID_GET_AT:
          serviceImpl.getAT((burst.kit.service.impl.grpc.BrsApi.GetByIdRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.AT>) responseObserver);
          break;
        case METHODID_GET_ATIDS:
          serviceImpl.getATIds((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.ATIds>) responseObserver);
          break;
        case METHODID_GET_BLOCK:
          serviceImpl.getBlock((burst.kit.service.impl.grpc.BrsApi.GetBlockRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Block>) responseObserver);
          break;
        case METHODID_GET_BLOCKS:
          serviceImpl.getBlocks((burst.kit.service.impl.grpc.BrsApi.GetBlocksRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Blocks>) responseObserver);
          break;
        case METHODID_GET_CONSTANTS:
          serviceImpl.getConstants((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Constants>) responseObserver);
          break;
        case METHODID_GET_COUNTS:
          serviceImpl.getCounts((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Counts>) responseObserver);
          break;
        case METHODID_GET_CURRENT_TIME:
          serviceImpl.getCurrentTime((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Time>) responseObserver);
          break;
        case METHODID_GET_DGS_GOOD:
          serviceImpl.getDgsGood((burst.kit.service.impl.grpc.BrsApi.GetByIdRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsGood>) responseObserver);
          break;
        case METHODID_GET_DGS_GOODS:
          serviceImpl.getDgsGoods((burst.kit.service.impl.grpc.BrsApi.GetDgsGoodsRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsGoods>) responseObserver);
          break;
        case METHODID_GET_DGS_PENDING_PURCHASES:
          serviceImpl.getDgsPendingPurchases((burst.kit.service.impl.grpc.BrsApi.GetDgsPendingPurchasesRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchases>) responseObserver);
          break;
        case METHODID_GET_DGS_PURCHASE:
          serviceImpl.getDgsPurchase((burst.kit.service.impl.grpc.BrsApi.GetByIdRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchase>) responseObserver);
          break;
        case METHODID_GET_DGS_PURCHASES:
          serviceImpl.getDgsPurchases((burst.kit.service.impl.grpc.BrsApi.GetDgsPurchasesRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.DgsPurchases>) responseObserver);
          break;
        case METHODID_GET_ESCROW_TRANSACTION:
          serviceImpl.getEscrowTransaction((burst.kit.service.impl.grpc.BrsApi.GetByIdRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.EscrowTransaction>) responseObserver);
          break;
        case METHODID_GET_MINING_INFO:
          serviceImpl.getMiningInfo((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.MiningInfo>) responseObserver);
          break;
        case METHODID_GET_ORDER:
          serviceImpl.getOrder((burst.kit.service.impl.grpc.BrsApi.GetOrderRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Order>) responseObserver);
          break;
        case METHODID_GET_ORDERS:
          serviceImpl.getOrders((burst.kit.service.impl.grpc.BrsApi.GetOrdersRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Orders>) responseObserver);
          break;
        case METHODID_GET_PEER:
          serviceImpl.getPeer((burst.kit.service.impl.grpc.BrsApi.GetPeerRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Peer>) responseObserver);
          break;
        case METHODID_GET_PEERS:
          serviceImpl.getPeers((burst.kit.service.impl.grpc.BrsApi.GetPeersRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Peers>) responseObserver);
          break;
        case METHODID_GET_STATE:
          serviceImpl.getState((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.State>) responseObserver);
          break;
        case METHODID_GET_SUBSCRIPTION:
          serviceImpl.getSubscription((burst.kit.service.impl.grpc.BrsApi.GetByIdRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscription>) responseObserver);
          break;
        case METHODID_GET_SUBSCRIPTIONS_TO_ACCOUNT:
          serviceImpl.getSubscriptionsToAccount((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Subscriptions>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION:
          serviceImpl.getTransaction((burst.kit.service.impl.grpc.BrsApi.GetTransactionRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.Transaction>) responseObserver);
          break;
        case METHODID_GET_TRANSACTION_BYTES:
          serviceImpl.getTransactionBytes((burst.kit.service.impl.grpc.BrsApi.BasicTransaction) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.TransactionBytes>) responseObserver);
          break;
        case METHODID_GET_UNCONFIRMED_TRANSACTIONS:
          serviceImpl.getUnconfirmedTransactions((burst.kit.service.impl.grpc.BrsApi.GetAccountRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.UnconfirmedTransactions>) responseObserver);
          break;
        case METHODID_PARSE_TRANSACTION:
          serviceImpl.parseTransaction((burst.kit.service.impl.grpc.BrsApi.TransactionBytes) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.BasicTransaction>) responseObserver);
          break;
        case METHODID_SUBMIT_NONCE:
          serviceImpl.submitNonce((burst.kit.service.impl.grpc.BrsApi.SubmitNonceRequest) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.SubmitNonceResponse>) responseObserver);
          break;
        case METHODID_SUGGEST_FEE:
          serviceImpl.suggestFee((com.google.protobuf.Empty) request,
              (io.grpc.stub.StreamObserver<burst.kit.service.impl.grpc.BrsApi.FeeSuggestion>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class BrsApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    BrsApiServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return burst.kit.service.impl.grpc.BrsApi.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("BrsApiService");
    }
  }

  private static final class BrsApiServiceFileDescriptorSupplier
      extends BrsApiServiceBaseDescriptorSupplier {
    BrsApiServiceFileDescriptorSupplier() {}
  }

  private static final class BrsApiServiceMethodDescriptorSupplier
      extends BrsApiServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    BrsApiServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (BrsApiServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new BrsApiServiceFileDescriptorSupplier())
              .addMethod(getBroadcastTransactionMethod())
              .addMethod(getBroadcastTransactionBytesMethod())
              .addMethod(getCompleteBasicTransactionMethod())
              .addMethod(getGetAccountMethod())
              .addMethod(getGetAccountATsMethod())
              .addMethod(getGetAccountBlocksMethod())
              .addMethod(getGetAccountCurrentOrdersMethod())
              .addMethod(getGetAccountEscrowTransactionsMethod())
              .addMethod(getGetAccountsMethod())
              .addMethod(getGetAccountSubscriptionsMethod())
              .addMethod(getGetAccountTransactionsMethod())
              .addMethod(getGetAliasMethod())
              .addMethod(getGetAliasesMethod())
              .addMethod(getGetAssetMethod())
              .addMethod(getGetAssetBalancesMethod())
              .addMethod(getGetAssetsMethod())
              .addMethod(getGetAssetsByIssuerMethod())
              .addMethod(getGetAssetTradesMethod())
              .addMethod(getGetAssetTransfersMethod())
              .addMethod(getGetATMethod())
              .addMethod(getGetATIdsMethod())
              .addMethod(getGetBlockMethod())
              .addMethod(getGetBlocksMethod())
              .addMethod(getGetConstantsMethod())
              .addMethod(getGetCountsMethod())
              .addMethod(getGetCurrentTimeMethod())
              .addMethod(getGetDgsGoodMethod())
              .addMethod(getGetDgsGoodsMethod())
              .addMethod(getGetDgsPendingPurchasesMethod())
              .addMethod(getGetDgsPurchaseMethod())
              .addMethod(getGetDgsPurchasesMethod())
              .addMethod(getGetEscrowTransactionMethod())
              .addMethod(getGetMiningInfoMethod())
              .addMethod(getGetOrderMethod())
              .addMethod(getGetOrdersMethod())
              .addMethod(getGetPeerMethod())
              .addMethod(getGetPeersMethod())
              .addMethod(getGetStateMethod())
              .addMethod(getGetSubscriptionMethod())
              .addMethod(getGetSubscriptionsToAccountMethod())
              .addMethod(getGetTransactionMethod())
              .addMethod(getGetTransactionBytesMethod())
              .addMethod(getGetUnconfirmedTransactionsMethod())
              .addMethod(getParseTransactionMethod())
              .addMethod(getSubmitNonceMethod())
              .addMethod(getSuggestFeeMethod())
              .build();
        }
      }
    }
    return result;
  }
}
